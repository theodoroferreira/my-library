package br.com.mylibrary.application.service;

import br.com.mylibrary.application.ports.in.BorrowingUseCase;
import br.com.mylibrary.domain.dto.PageableBorrowingDto;
import br.com.mylibrary.domain.dto.request.BorrowingRequestDto;
import br.com.mylibrary.domain.dto.response.BorrowingResponseDto;
import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.domain.model.Borrowing;
import br.com.mylibrary.domain.model.User;
import br.com.mylibrary.framework.adapter.out.repository.BookRepository;
import br.com.mylibrary.framework.adapter.out.repository.BorrowingRepository;
import br.com.mylibrary.framework.adapter.out.repository.UserRepository;
import br.com.mylibrary.framework.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowingService implements BorrowingUseCase {

    private final BorrowingRepository borrowingRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final BookService bookService;

    private final ModelMapper modelMapper;

    @Override
    public BorrowingResponseDto create(BorrowingRequestDto request) {
        Optional<Book> book = bookRepository.findById(UUID.fromString(request.getBookId()));
        Optional<User> user = userRepository.findByCpf(request.getCpf());

        user.get().setTotalBorrowings(user.get().getTotalBorrowings() + 1);

        if (Objects.equals(book.get().getStatus(), "INDISPONIVEL")) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro indisponível para ser retirado");
        }

        Borrowing borrowing = new Borrowing();

        borrowing.setBook(book.get());
        borrowing.setUser(user.get());
        borrowing.setBorrowingDate(LocalDate.now());
        borrowing.setReturnDate(borrowing.getBorrowingDate().plusDays(7));

        book.get().setUnits(book.get().getUnits() - 1);
        bookRepository.save(book.get());

        borrowingRepository.save(borrowing);

        if (book.get().getUnits().compareTo(0) == 0) {
            bookService.checkAndUpdateBookStatus(book.get().getId());
        }

        return modelMapper.map(borrowing, BorrowingResponseDto.class);
    }

    @Override
    public PageableBorrowingDto findAll(LocalDate borrowingDate, LocalDate returnDate, Pageable pageable) {
        Page<Borrowing> page = borrowingRepository.findAllByFilters(borrowingDate, returnDate, pageable);

        if (page.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Nenhum empréstimo encontrado.");
        }

        List<BorrowingResponseDto> borrowings = List.of(modelMapper.map(page.getContent(), BorrowingResponseDto.class));

        return PageableBorrowingDto
                .builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .borrwings(borrowings)
                .build();
    }

    @Override
    public BorrowingResponseDto findById(Long id) {
        Optional<Borrowing> borrowing = borrowingRepository.findById(id);

        if (borrowing.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Empréstimo não encontrado!");
        }

        return modelMapper.map(borrowing.get(), BorrowingResponseDto.class);
    }
}
