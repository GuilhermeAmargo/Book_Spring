package com.senai.exec1.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senai.exec1.entities.Books;
import com.senai.exec1.repositories.BooksRepository;
import com.senai.exec1.services.BookService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/books")
public class BooksController {
    
    @Autowired
    private BookService bookService;


    // Get
    @GetMapping
    public ResponseEntity<List<Books>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get id
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    // Post
    @PostMapping
    public ResponseEntity<Books> saveBook(@RequestBody Books book) {
        Books newBook = bookService.saveBook(book);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newBook.getId())
            .toUri();
        return ResponseEntity.created(location).body(newBook);
    }

    // Put
    @RequestMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Books book) {
        bookService.updateBook(id, book);
        return ResponseEntity.noContent().build();
    }
    
}
