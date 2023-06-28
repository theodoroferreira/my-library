package br.com.mylibrary.framework.adapter.in;

import br.com.mylibrary.application.ports.in.BookUseCase;
import br.com.mylibrary.domain.dto.PageableDto;
import br.com.mylibrary.domain.dto.request.BookRequestDto;
import br.com.mylibrary.domain.dto.response.BookResponseDto;
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
    public ResponseEntity<BookResponseDto> create(@RequestBody @Valid BookRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @GetMapping
    public PageableDto findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String author, @RequestParam(required = false) String category, Pageable pageable) {
        return bookService.findAll(name, author, category, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable UUID id, @RequestBody @Valid BookRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
