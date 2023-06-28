package br.com.mylibrary.application.ports.in;

import br.com.mylibrary.domain.dto.PageableBorrowingDto;
import br.com.mylibrary.domain.dto.request.BorrowingRequestDto;
import br.com.mylibrary.domain.dto.response.BorrowingResponseDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface BorrowingUseCase {

    BorrowingResponseDto create(BorrowingRequestDto request);

    PageableBorrowingDto findAll(LocalDate borrowingDate, LocalDate returnDate, Pageable pageable);

    BorrowingResponseDto findById(Long id);
}
