package com.senai.exec1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.exec1.dtos.BookRequest;
import com.senai.exec1.dtos.BookResponse;
import com.senai.exec1.entities.Books;
import com.senai.exec1.mappers.BookMapper;
import com.senai.exec1.repositories.BooksRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    
    @Autowired
    private BooksRepository booksRepository;

    public List <BookResponse> getAllBooks() {
            return booksRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .toList();
    }

    public BookResponse getBookById(Long id) {
        return booksRepository.findById(id)
        .map(BookMapper::toDTO)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado: " + id));
    }

    public void deleteBook(Long id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Livro não encontrado: " + id);
        }
    }

    public BookResponse saveBook(BookRequest request) {
        Books book = BookMapper.toEntity(request);
        Books savedBook = booksRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    public void updateBook(Long id, BookRequest request) {
        Books aux = booksRepository.getReferenceById(id);
        aux.setAuthor(request.author());
        aux.setName(request.name());
        aux.setPrice(request.price());
        aux.setReleaseyear(request.releaseyear());
        
        booksRepository.save(aux);

    }
}
