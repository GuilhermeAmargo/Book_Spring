package com.senai.exec1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senai.exec1.dtos.BookRequest;
import com.senai.exec1.dtos.BookResponse;
import com.senai.exec1.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    
    @Autowired
    private BookService bookService;


    // Get
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get id
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
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
    public ResponseEntity<BookResponse> saveBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.saveBook(request);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }

    // Put
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id,@Valid @RequestBody BookRequest request) {
        bookService.updateBook(id, request);
        return ResponseEntity.noContent().build();
    }
    
}
