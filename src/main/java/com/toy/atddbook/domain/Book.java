package com.toy.atddbook.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @Builder.Default
    private boolean isRent = true;

    public void rent() {
        if (!isRent) {
            throw new RuntimeException("대여 할 수 없는 책입니다.");
        }
        isRent = false;
    }

}
