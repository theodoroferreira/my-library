package br.com.mylibrary.application.service;

import br.com.mylibrary.application.ports.in.BookUseCase;
import br.com.mylibrary.domain.dto.PageableDto;
import br.com.mylibrary.domain.dto.request.BookRequestDto;
import br.com.mylibrary.domain.dto.response.BookResponseDto;
import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.framework.adapter.out.repository.BookRepository;
import br.com.mylibrary.framework.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService implements BookUseCase {

    private final BookRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto create(BookRequestDto request) {
        Book book = modelMapper.map(request, Book.class);

        book.setUnits(request.getBookCodes().size());
        book.setStatus("DISPONIVEL");

        Optional<Book> optional = repository.findBookByName(book.getName());

        if (optional.isEmpty()) {
            return modelMapper.map(repository.save(book), BookResponseDto.class);
        } else {
            throw new GenericException(HttpStatus.BAD_REQUEST,
                    "Já existe um livro cadastrado com o nome: " + request.getName());
        }
    }

    @Override
    public PageableDto findAll(String name, String author, String category, Pageable pageable) {
        Page<Book> page = repository.findAllByFilters(name, author, category, pageable);

        if (page.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Nenhum livro encontrado.");
        }

        List<BookResponseDto> books = List.of(modelMapper.map(page.getContent(), BookResponseDto.class));

        return PageableDto
                .builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .books(books)
                .build();
    }

    @Override
    public BookResponseDto findById(UUID id) {
        Optional<Book> book = repository.findById(id);

        if(book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }
        return modelMapper.map(book.get(), BookResponseDto.class);
    }

    @Override
    public BookResponseDto update(UUID id, BookRequestDto request) {
        Optional<Book> book = repository.findById(id);

        if (book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }

        book.get().setCategory(request.getCategory());
        repository.save(book.get());

        return modelMapper.map(book.get(), BookResponseDto.class);
    }

    @Override
    public void delete(UUID id) {
        Optional<Book> book = repository.findById(id);

        if (book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }

        repository.deleteById(id);
    }

    public void checkAndUpdateBookStatus(UUID bookId) {
        Optional<Book> bookOptional = repository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (book.getUnits().compareTo(0) == 0) {
                book.setStatus("INDISPONIVEL");
                repository.save(book);
            }
        }
    }
}
