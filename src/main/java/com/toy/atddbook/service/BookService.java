package com.toy.atddbook.service;

import com.toy.atddbook.domain.Book;
import com.toy.atddbook.dto.BookRequest;
import com.toy.atddbook.dto.BookResponse;
import com.toy.atddbook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse rentBook(long bookId) {
        Book book = getBook(bookId);
        book.rent();
        return BookResponse.of(bookRepository.save(book));
    }

    private Book getBook(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
    }

    public List<BookResponse> getBookList() {
        return bookRepository.findAll().stream().map(BookResponse::of).toList();
    }

    public BookResponse getBookResponse(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        return BookResponse.of(bookOptional.orElseThrow(IllegalArgumentException::new));
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        return BookResponse.of(bookRepository.save(bookRequest.toEntity()));
    }
}
