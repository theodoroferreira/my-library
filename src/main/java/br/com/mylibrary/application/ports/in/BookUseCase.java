package br.com.mylibrary.application.ports.in;

import br.com.mylibrary.domain.dto.BookDto;
import br.com.mylibrary.domain.dto.PageableDTO;
import br.com.mylibrary.domain.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookUseCase {

    Book create(BookDto request);

    PageableDTO findAll(String name, String category, Pageable pageable);

    BookDto findById(UUID id);

    Book update(UUID id, BookDto request);

    void delete(UUID id);
}
