package com.ahmed.challenge2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


   @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String publication_year;
    @NotBlank
    private String isbn;
    private boolean isAvailable;

}

