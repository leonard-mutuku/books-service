/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service;

import com.abc.exception.EntityNotFoundException;
import com.abc.model.Book;
import com.abc.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leonard
 */
@Service
public class BookService {

    @Autowired
    private BookRepository br;

    public Book saveBook(Book newBook) {
        return br.save(newBook);
    }

    public List<Book> getBooks() {
        return br.findAll();
    }

    public Book getBookById(Long id) {
        return br.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public Book updateBookById(Long id, Book newBook) {
        Optional<Book> optionalBook = br.findById(id);
        if (optionalBook.isPresent()) {
//            Book opbk = optionalBook.get();

            Book book = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getYear());
            return br.save(book);
        } else {
            String msg = "Book with ID " + id + " not found for update!.";
            throw new EntityNotFoundException(msg);
        }
    }

    public void deleteBookById(Long id) {
        br.deleteById(id);
    }

}
