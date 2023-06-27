package br.com.mylibrary.framework.adapter.in;

import br.com.mylibrary.application.ports.in.BorrowingUseCase;
import br.com.mylibrary.domain.dto.BookDto;
import br.com.mylibrary.domain.dto.BorrowingDto;
import br.com.mylibrary.domain.dto.PageableDTO;
import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.domain.model.Borrowing;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/borrow")
@RequiredArgsConstructor
@RestController
public class BorrowingController {

    private final BorrowingUseCase borrowingService;

    @PostMapping
    public ResponseEntity<Borrowing> create(@RequestBody @Valid BorrowingDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingService.create(request));
    }

    @GetMapping
    public PageableDTO findAll(@RequestParam(required = false) String userName, Pageable pageable) {
        return borrowingService.findAll(userName, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowingService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrowing> update(@PathVariable Long id, @RequestBody @Valid BorrowingDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowingService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        borrowingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
