package com.senai.exec1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.exec1.entities.Books;

public interface BooksRepository extends JpaRepository<Books, Long>{
    
}
