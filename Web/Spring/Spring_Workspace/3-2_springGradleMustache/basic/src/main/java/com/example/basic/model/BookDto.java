package com.example.basic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private Boolean isBn;
    private String bookTitle;
    private String author;
    private Double price;
}
