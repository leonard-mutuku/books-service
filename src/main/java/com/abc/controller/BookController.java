/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.controller;

import com.abc.model.Book;
import com.abc.service.BookService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author leonard
 */
@RestController
public class BookController {

    @Autowired
    private BookService bs;

    @PostMapping(path = "/books")
    public ResponseEntity saveBook(@Valid @RequestBody Book book) {
        Book newBook = bs.saveBook(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.id())
                .toUri();
        return ResponseEntity.created(uri).body("Book created successful.");
    }

    @GetMapping(path = "/books")
    public ResponseEntity getBooks() {
        List<Book> books = bs.getBooks();

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping(path = "/books/{book_id}")
    public ResponseEntity getBook(@PathVariable("book_id") Long bookId) {
        Book book = bs.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PutMapping(path = "/books/{book_id}")
    public ResponseEntity updateBook(
            @PathVariable("book_id") Long bookId,
            @Valid @RequestBody Book book
    ) {
        bs.updateBookById(bookId, book);

        return ResponseEntity.status(HttpStatus.OK).body("Book update successful.");
    }

    @DeleteMapping(path = "/books/{book_id}")
    public ResponseEntity deleteUser(@PathVariable("book_id") Long bookId) {
        bs.deleteBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successful.");
    }

}
