package br.com.mylibrary.application.ports.in;

import br.com.mylibrary.domain.dto.BorrowingDto;
import br.com.mylibrary.domain.dto.PageableDTO;
import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.domain.model.Borrowing;
import org.springframework.data.domain.Pageable;

public interface BorrowingUseCase {

    Borrowing create(BorrowingDto request);

    PageableDTO findAll(String userName, Pageable pageable);

    Borrowing findById(Long id);

    Borrowing update(Long id, BorrowingDto request);

    void delete(Long id);
}
