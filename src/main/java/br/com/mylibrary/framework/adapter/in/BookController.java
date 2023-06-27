package br.com.mylibrary.framework.adapter.in;

import br.com.mylibrary.application.ports.in.BookUseCase;
import br.com.mylibrary.application.service.BookService;
import br.com.mylibrary.domain.dto.BookDto;
import br.com.mylibrary.domain.dto.PageableDTO;
import br.com.mylibrary.domain.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/books")
@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookUseCase bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid BookDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @GetMapping
    public PageableDTO findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String category, Pageable pageable) {
        return bookService.findAll(name, category, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable UUID id, @RequestBody @Valid BookDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
