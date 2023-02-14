package com.toy.atddbook.controller;

import com.toy.atddbook.dto.BookRequest;
import com.toy.atddbook.dto.BookResponse;
import com.toy.atddbook.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> getBookList() {
        return ResponseEntity.ok(bookService.getBookList());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable long bookId) {
        return ResponseEntity.ok(bookService.getBookResponse(bookId));
    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRequest));
    }

    @PostMapping("/rent/{bookId}")
    public ResponseEntity<BookResponse> rentBook(@PathVariable long bookId) {
        return ResponseEntity.ok(bookService.rentBook(bookId));
    }

}
