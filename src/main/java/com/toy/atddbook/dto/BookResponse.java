package com.toy.atddbook.dto;

import com.toy.atddbook.domain.Book;
import lombok.Builder;

@Builder
public record BookResponse(long id,
                           String bookName,
                           boolean isRent) {

    public static BookResponse of(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .isRent(book.isRent())
                .build();
    }

}
