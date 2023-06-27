package br.com.mylibrary.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo nome não deve estar vazio.")
    private String name;

    @NotBlank(message = "Campo autor não deve estar vazio.")
    private String author;

    @NotBlank(message = "Campo ano não deve estar vazio.")
    private String year;

//    @NotBlank(message = "Campo categoria não deve estar vazio.")
    private String category;

    private BigDecimal units;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;
}
