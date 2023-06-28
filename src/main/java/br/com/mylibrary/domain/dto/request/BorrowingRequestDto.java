package br.com.mylibrary.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingRequestDto {

    @NotNull(message = "Campo de id do livro não pode estar nulo.")
    @UUID(message = "Id inválido")
    private String bookId;

    @NotNull(message = "Campo de cpf não pode estar nulo.")
    @CPF(message = "CPF inválido")
    private String cpf;
}
