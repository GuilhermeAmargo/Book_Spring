package com.senai.exec1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.exec1.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long>{
    
}
