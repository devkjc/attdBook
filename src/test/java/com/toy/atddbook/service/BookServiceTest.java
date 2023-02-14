package com.toy.atddbook.service;

import com.toy.atddbook.DatabaseCleanup;
import com.toy.atddbook.domain.Book;
import com.toy.atddbook.dto.BookRequest;
import com.toy.atddbook.dto.BookResponse;
import com.toy.atddbook.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "test")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
    }

    @Test
    void 책_저장() {
        //given
        BookRequest bookRequest = getBookRequest("bookName1");
        //when
        BookResponse bookResponse = bookService.saveBook(bookRequest);
        //then
        assertThat(bookResponse.bookName()).isEqualTo(bookRequest.bookName());
    }

    @Test
    void 책_조회() {
        //given
        Book saveBook = saveBook("bookName1");
        //when
        BookResponse findResponse = bookService.getBookResponse(saveBook.getId());
        //then
        assertThat(findResponse.bookName()).isEqualTo(saveBook.getBookName());
    }

    @Test
    void 책_대여() {
        //given
        Book saveBook = saveBook("bookName1");
        assertThat(saveBook.isRent()).isTrue();
        Book book = bookRepository.findById(saveBook.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(book.isRent()).isTrue();
        //when
        book.rent();
        Book rentBook = bookRepository.save(book);
        //then
        assertThat(rentBook.isRent()).isFalse();
    }

    @Test
    void 책_목록_조회() {
        //given
        int size = 15;
        IntStream.range(0, size).forEach(value -> {
            saveBook("bookName" + value);
        });
        //when
        List<Book> all = bookRepository.findAll();
        List<BookResponse> bookResponses = all.stream().map(BookResponse::of).toList();

        //then
        assertThat(all.size()).isEqualTo(size);
        assertThat(all.stream().map(Book::getBookName).toList()
                .containsAll(Arrays.asList("bookName0","bookName5", "bookName9"))).isTrue();
        assertThat(all.size()).isEqualTo(bookResponses.size());

    }

    private Book saveBook(String bookName) {
        return bookRepository.save(getBookRequest(bookName).toEntity());
    }

    private static BookRequest getBookRequest(String bookName) {
        return BookRequest.builder().bookName(bookName).build();
    }
}
