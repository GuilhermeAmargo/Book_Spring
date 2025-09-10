package com.senai.exec1.mappers;

import com.senai.exec1.dtos.BookRequest;
import com.senai.exec1.dtos.BookResponse;
import com.senai.exec1.entities.Books;

public class BookMapper {

    public static Books toEntity(BookRequest bookRequest) {
        Books b = new Books();
        b.setName(bookRequest.name());
        b.setAuthor(bookRequest.author());
        b.setPrice(bookRequest.price());
        b.setReleaseyear(bookRequest.releaseyear());
        return b;
    }
    
    public static BookResponse toDTO(Books book) {
        return new BookResponse(
            book.getId(),
            book.getName(),
            book.getAuthor(),
            book.getPrice(),
            book.getReleaseyear()
        );
    }
}
