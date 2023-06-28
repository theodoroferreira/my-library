package br.com.mylibrary.application.ports.in;

import br.com.mylibrary.domain.dto.PageableDto;
import br.com.mylibrary.domain.dto.request.BookRequestDto;
import br.com.mylibrary.domain.dto.response.BookResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookUseCase {

    BookResponseDto create(BookRequestDto request);

    PageableDto findAll(String name, String author, String category, Pageable pageable);

    BookResponseDto findById(UUID id);

    BookResponseDto update(UUID id, BookRequestDto request);

    void delete(UUID id);
}
