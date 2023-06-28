package br.com.mylibrary.domain.dto;

import br.com.mylibrary.domain.dto.response.BookResponseDto;
import br.com.mylibrary.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageableDto {

    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private List<Book> books;
}
