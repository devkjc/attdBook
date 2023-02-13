package com.toy.atddbook.dto;

import com.toy.atddbook.domain.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record BookRequest(@NotBlank String bookName) {

    public Book toEntity() {
        return Book.builder()
                .bookName(bookName)
                .build();
    }

}
