package com.toy.atddbook.repository;

import com.toy.atddbook.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void 책_저장() {
        //given
        Book book = Book.builder()
                .bookName("bookName1").build();

        //when
        Book saveBook = bookRepository.save(book);

        //then
        assertThat(book).isInstanceOf(Book.class);
        assertThat(book.isRent()).isTrue();
        assertThat(saveBook.getBookName()).isEqualTo(book.getBookName());
    }
}