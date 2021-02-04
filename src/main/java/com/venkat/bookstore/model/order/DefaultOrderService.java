package com.venkat.bookstore.model.order;

import com.venkat.bookstore.api.ApiException;
import com.venkat.bookstore.model.book.Book;
import com.venkat.bookstore.model.cart.ShoppingCart;
import com.venkat.bookstore.model.cart.ShoppingCartItem;
import com.venkat.bookstore.model.customer.Customer;
import com.venkat.bookstore.model.customer.CustomerForm;
import com.venkat.bookstore.repositories.BookRepository;
import com.venkat.bookstore.repositories.CustomerRepository;
import com.venkat.bookstore.repositories.LineItemRepository;
import com.venkat.bookstore.repositories.OrderRepository;
import com.venkat.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DefaultOrderService implements OrderService {

    Random random = new Random();

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    private final LineItemRepository lineItemRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public DefaultOrderService(BookRepository bookRepository, CustomerRepository customerRepository,OrderRepository orderRepository,LineItemRepository lineItemRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.lineItemRepository = lineItemRepository;
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
        validateCustomer(customerForm);
        validateCart(cart);
        LocalDate date = getLocalDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
        return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    date, cart);
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        CustomerOrder order = orderRepository.findByOrderIdEquals(orderId);
        Customer customer = customerRepository.findByCustomerIdEquals(order.getCustomerId());
        List<LineItem> lineItems = lineItemRepository.findByOrderIdEquals(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookRepository.findBookByBookIdEquals(lineItem.getBookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }

    private LocalDate getLocalDate(String monthString,
                                   String yearString) {
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);
        LocalDate init = LocalDate.of(year, month, 1);
        return init.withDayOfMonth(init.lengthOfMonth());
    }

    @Transactional(rollbackOn = Exception.class)
    long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, LocalDate date,
            ShoppingCart cart) {
        Customer customer = new Customer(name,address,phone,email,ccNumber,date);
        try {
            long customerId = customerRepository.save(customer).getCustomerId();
            CustomerOrder order = new CustomerOrder(cart.getComputedSubtotal()+cart.getSurcharge(),generateConfirmationNumber(),customerId);
            long orderId = orderRepository.save(order).getOrderId();
            for (ShoppingCartItem item : cart.getItems()) {
                LineItem lineItem = new LineItem(item.getBookId(),orderId,item.getQuantity());
                lineItemRepository.save(lineItem);
            }
            return orderId;
        } catch (Exception e) {
            return 0;
        }

//        try {
//            connection.setAutoCommit(false);
//            long customerId = customerDao.create(
//                    connection, name, address, phone, email,
//                    ccNumber, date);
//            long customerOrderId = orderDao.create(
//                    connection,
//                    cart.getComputedSubtotal() + cart.getSurcharge(),
//                    generateConfirmationNumber(), customerId);
//            for (ShoppingCartItem item : cart.getItems()) {
//                lineItemDao.create(connection, customerOrderId,
//                        item.getBookId(), item.getQuantity());
//            }
//            connection.commit();
//            return customerOrderId;
//        } catch (Exception e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                throw new BookstoreDbException("Failed to roll back transaction", e1);
//            }
//            return 0;
//        }
    }

    private int generateConfirmationNumber() {
        return random.nextInt(999999999);
    }

    private void validateCustomer(CustomerForm customerForm) {

        String name = customerForm.getName();

        if (name == null || name.equals("") || name.length() > 45 || name.length() < 4) {
            throw new ApiException.InvalidParameter("Invalid name field");
        }

        String address = customerForm.getAddress();

        if (address == null || address.equals("") || address.length() > 45 || address.length() < 4) {
            throw new ApiException.InvalidParameter("Invalid address field");
        }

        String phoneNumber = customerForm.getPhone();
        phoneNumber = phoneNumber.replaceAll("[()\\s\\-\\D+]","");

        if (phoneNumber == null || phoneNumber.equals("") || phoneNumber.length() != 10) {
            throw new ApiException.InvalidParameter("Invalid phone number");
        }

        String email = customerForm.getEmail();

        if (email == null || email.equals("") || !email.contains("@") || email.contains(" ") || email.charAt(email.length() - 1) == '.') {
            throw new ApiException.InvalidParameter("Invalid email field");
        }

        String ccNumber = customerForm.getCcNumber();

        ccNumber = ccNumber.replaceAll("[\\s\\-]","");

        if (ccNumber == null || ccNumber.equals("") || ccNumber.length() < 14 || ccNumber.length() > 16) {
            throw new ApiException.InvalidParameter("Invalid credit card number");
        }

        if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
            throw new ApiException.InvalidParameter("Invalid expiry date");

        }
    }

    private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
        try {
            int expMonth = Integer.parseInt(ccExpiryMonth);
            int expYear = Integer.parseInt(ccExpiryYear);
            YearMonth y = YearMonth.now();
            int month = y.getMonthValue();
            int year = y.getYear();
            return (expMonth < month && expYear == year) || expYear < year;
        }
        catch (Exception e) {
            throw new NumberFormatException();
        }
    }

    private void validateCart(ShoppingCart cart) {

        if (cart.getItems().size() <= 0) {
            throw new ApiException.InvalidParameter("Cart is empty.");
        }

        cart.getItems().forEach(item-> {
            if (item.getQuantity() <= 0 || item.getQuantity() > 99) {
                throw new ApiException.InvalidParameter("Invalid quantity");
            }
            Book databaseBook = bookRepository.findBookByBookIdEquals(item.getBookId());

            if (databaseBook.getPrice() != item.getBookForm().getPrice()) {
                throw new ApiException.InvalidParameter("Invalid price");
            }

            if (databaseBook.getCategoryId() != item.getBookForm().getCategoryId()) {
                throw new ApiException.InvalidParameter("Invalid category");
            }
        });
    }
}
