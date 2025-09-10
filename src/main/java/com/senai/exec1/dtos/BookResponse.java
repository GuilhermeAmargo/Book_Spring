package com.senai.exec1.dtos;

public record BookResponse(
    Long id,
    String name,
    String author,
    Double price,
    Integer releaseyear
) {
}
