package com.toy.atddbook.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BookTest {

    @Test
    void 책_대여() {
        //given
        Book book1 = Book.builder().bookName("book1").build();
        assertThat(book1.isRent()).isEqualTo(true);
        //when
        book1.rent();
        //then
        assertThat(book1.isRent()).isEqualTo(false);
    }

    @Test
    void 책_대여_실패() {
        //given
        Book book1 = Book.builder().bookName("book1").build();
        book1.rent();
        //then
        assertThatThrownBy(book1::rent).isInstanceOf(RuntimeException.class);
    }
}