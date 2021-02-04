package com.venkat.bookstore.repositories;

import com.venkat.bookstore.model.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookByBookIdEquals(long bookId);

    List<Book> findBookByCategoryIdNot(long categoryId);
}
