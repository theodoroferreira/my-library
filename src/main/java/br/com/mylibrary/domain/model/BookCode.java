package br.com.mylibrary.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_codes")
public class BookCode {

    @Id
    @NotNull(message = "Campo código não pode estar nulo")
    private String code;
}
