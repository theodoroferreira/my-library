package br.com.mylibrary.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo nome não deve estar em branco.")
    private String name;

    @NotBlank(message = "Campo sobrenome não deve estar em branco.")
    private String surname;

    @CPF(message = "CPF inválido")
    private String cpf;

    private BigDecimal totalBorrowings;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;
}
