package com.senai.exec1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.exec1.entity.Books;
import com.senai.exec1.repository.BooksRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("/{id}")
    public Books getBookById(@PathVariable Long id) {
        return booksRepository.findById(id).orElseThrow(  () -> new EntityNotFoundException("Nao cadastadro"));
    }

    @GetMapping
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }
    
}
