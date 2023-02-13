package com.toy.atddbook.service;

import com.toy.atddbook.dto.BookRequest;
import com.toy.atddbook.dto.BookResponse;
import com.toy.atddbook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse rentBook(long bookId) {

        return null;
    }

    public List<BookResponse> getBookList() {
        return null;
    }

    public BookResponse getBook(long bookId) {
        return null;
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        return null;
    }
}
