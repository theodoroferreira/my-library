package br.com.mylibrary.domain.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "user_id")
    private String cpf;

    private String name;

    private String surname;

    private String email;

    private Integer totalBorrowings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;

}
