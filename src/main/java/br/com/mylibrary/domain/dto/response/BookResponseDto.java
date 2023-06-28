package br.com.mylibrary.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private UUID id;

    private String name;

    private String author;

    private String year;

    private String category;

    private Integer units;

    private String status;

    private List<BorrowingResponseDto> borrowings;
}
