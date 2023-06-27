package br.com.mylibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private String name;

    private String author;

    private String year;

    private String category;

    private BigDecimal units;
}
