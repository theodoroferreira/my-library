package br.com.mylibrary.application.service;

import br.com.mylibrary.application.ports.in.BorrowingUseCase;
import br.com.mylibrary.domain.dto.BookDto;
import br.com.mylibrary.domain.dto.BorrowingDto;
import br.com.mylibrary.domain.dto.PageableDTO;
import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.domain.model.Borrowing;
import br.com.mylibrary.framework.adapter.out.repository.BookRepository;
import br.com.mylibrary.framework.adapter.out.repository.BorrowingRepository;
import br.com.mylibrary.framework.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowingService implements BorrowingUseCase {

    private final BorrowingRepository borrowingRepository;

    private final BookRepository bookRepository;

    private final BookService bookService;

    private final ModelMapper modelMapper;
    @Override
    public Borrowing create(BorrowingDto request) {
        Optional<Book> book = bookRepository.findById(UUID.fromString(request.getBookId()));

        Borrowing borrowing = new Borrowing();

        borrowing.setBook(book.get());
        borrowing.setBorrowingDate(LocalDate.now());
        borrowing.setReturnDate(borrowing.getBorrowingDate().plusDays(7));

        bookService.update(book.get().getId(), BookDto.builder()
                .units(book.get()
                    .getUnits()
                    .subtract(BigDecimal.valueOf(1)))
                .build());

        borrowingRepository.save(borrowing);

        return borrowing;
    }

    @Override
    public PageableDTO findAll(String userName, Pageable pageable) {
        return null;
    }

    @Override
    public Borrowing findById(Long id) {
        Optional<Borrowing> borrowing = borrowingRepository.findById(id);

        if(borrowing.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Empréstimo não encontrado!");
        }

        return modelMapper.map(borrowing, Borrowing.class);
    }

    @Override
    public Borrowing update(Long id, BorrowingDto request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
