package com.toy.atddbook.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_list")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
