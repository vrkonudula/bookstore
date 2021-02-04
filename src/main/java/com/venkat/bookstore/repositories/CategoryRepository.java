package com.venkat.bookstore.repositories;

import com.venkat.bookstore.model.book.Book;
import com.venkat.bookstore.model.category.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findCategoryByCategoryIdEquals(long categoryId);

    Category findCategoryByNameEquals(String name);

}
