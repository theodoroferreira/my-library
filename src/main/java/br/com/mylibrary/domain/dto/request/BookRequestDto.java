package br.com.mylibrary.domain.dto.request;

import br.com.mylibrary.domain.model.BookCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {

    @NotBlank(message = "Campo nome não deve estar vazio.")
    private String name;

    @NotBlank(message = "Campo autor não deve estar vazio.")
    private String author;

    @NotBlank(message = "Campo ano não deve estar vazio.")
    @Pattern(regexp = "^[0-9]+$", message = "Campo ano só aceita números.")
    private String year;

    @NotBlank(message = "Campo categoria não deve estar vazio.")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçÁÀÂÃÉÈÍÏÓÔÕÖÚÇ ]+$", message = "Campo categoria não aceita números ou caracteres especiais.")
    private String category;

    @NotNull(message = "Campo codigo de barras não deve estar nulo.")
    private List<BookCode> bookCodes;
}
