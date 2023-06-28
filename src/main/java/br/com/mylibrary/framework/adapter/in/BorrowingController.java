package br.com.mylibrary.framework.adapter.in;

import br.com.mylibrary.application.ports.in.BorrowingUseCase;
import br.com.mylibrary.domain.dto.PageableBorrowingDto;
import br.com.mylibrary.domain.dto.request.BorrowingRequestDto;
import br.com.mylibrary.domain.dto.PageableDto;
import br.com.mylibrary.domain.dto.response.BorrowingResponseDto;
import br.com.mylibrary.domain.model.Borrowing;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping(value = "/borrow")
@RequiredArgsConstructor
@RestController
public class BorrowingController {

    private final BorrowingUseCase borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingResponseDto> create(@RequestBody @Valid BorrowingRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingService.create(request));
    }

    @GetMapping
    public PageableBorrowingDto findAll(@RequestParam(required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate borrowingDate,
                                        @RequestParam(required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate returnDate,
                                        Pageable pageable) {
        return borrowingService.findAll(borrowingDate, returnDate, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowingService.findById(id));
    }
}
