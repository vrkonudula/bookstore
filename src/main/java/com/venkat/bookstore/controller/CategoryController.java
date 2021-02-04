package com.venkat.bookstore.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.venkat.bookstore.model.book.Book;
import com.venkat.bookstore.model.category.Category;
import com.venkat.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public @ResponseBody List<Category> categories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<Category> categoryById(@PathVariable long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryIdEquals(categoryId);
        return (category == null)?ResponseEntity.notFound().build():ResponseEntity.ok(category);
    }

    @GetMapping(path = "/name/{categoryName}")
    public ResponseEntity<Category> categoryByName(@PathVariable String categoryName) {
        Category category = categoryRepository.findCategoryByNameEquals(categoryName);
        return (category==null)?ResponseEntity.notFound().build():ResponseEntity.ok(category);
    }

    @GetMapping(path = "/{categoryId}/books")
    public ResponseEntity<List<Book>> categoryBooksById(@PathVariable long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryIdEquals(categoryId);
        return (category.getBooks()==null)?ResponseEntity.notFound().build():ResponseEntity.ok(category.getBooks());
    }

    @GetMapping(path = "/name/{categoryName}/books")
    public ResponseEntity<List<Book>> categoryBooksById(@PathVariable String categoryName) {
        Category category = categoryRepository.findCategoryByNameEquals(categoryName);
        return (category.getBooks()==null)?ResponseEntity.notFound().build():ResponseEntity.ok(category.getBooks());
    }

    @GetMapping(path = "/{categoryId}/suggested-books")
    public ResponseEntity<List<Book>> suggestedBooksByCategoryName(@PathVariable long categoryId,@RequestParam(defaultValue = "3") int limit) {
        Category category = categoryRepository.findCategoryByCategoryIdEquals(categoryId);
        if (category.getBooks() == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            List<Book> books = category.getBooks();
            Collections.shuffle(books);
            return ResponseEntity.ok(books.subList(0,limit));
        }
    }

    @GetMapping(path = "/name/{categoryName}/suggested-books")
    public ResponseEntity<List<Book>> suggestedBooksByCategoryName(@PathVariable String categoryName,@RequestParam(defaultValue = "3") int limit) {
        Category category = categoryRepository.findCategoryByNameEquals(categoryName);
        if (category.getBooks() == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            List<Book> books = category.getBooks();
            Collections.shuffle(books);
            return ResponseEntity.ok(books.subList(0,limit));
        }
    }

}
