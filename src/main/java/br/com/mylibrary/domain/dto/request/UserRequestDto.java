package br.com.mylibrary.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "Campo de nome não pode estar vazio.")
    private String name;

    @NotBlank(message = "Campo de sobrenome não pode estar vazio.")
    private String surname;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Email(message = "Endereço de email inválido")
    private String email;
}
