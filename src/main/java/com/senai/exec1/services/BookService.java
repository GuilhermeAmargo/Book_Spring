package com.senai.exec1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.exec1.entities.Books;
import com.senai.exec1.repositories.BooksRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    
    @Autowired
    private BooksRepository booksRepository;

    public List <Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Books getBookById(Long id) {
        return booksRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado: " + id));
    }

    public void deleteBook(Long id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Livro não encontrado: " + id);
        }
    }

    public Books saveBook(Books book) {
        return booksRepository.save(book);
    }

    public void updateBook(Long id, Books book) {
        Books aux = booksRepository.getReferenceById(id);
        aux.setAuthor(book.getAuthor());
        aux.setName(book.getName());
        aux.setPrice(book.getPrice());
        aux.setReleaseyear(book.getReleaseyear());
        
        booksRepository.save(aux);

    }
}
