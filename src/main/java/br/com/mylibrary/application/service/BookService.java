package br.com.mylibrary.application.service;

import br.com.mylibrary.application.ports.in.BookUseCase;
import br.com.mylibrary.domain.dto.BookDto;
import br.com.mylibrary.domain.dto.PageableDTO;
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
    public Book create(BookDto request) {
        Book book = modelMapper.map(request, Book.class);

        Optional<Book> optional = repository.findBookByName(book.getName());

        if (optional.isEmpty()) {
            return repository.save(book);
        } else {
            throw new GenericException(HttpStatus.BAD_REQUEST,
                    "Já existe um livro cadastrado com o nome: " + request.getName());
        }
    }

    @Override
    public PageableDTO findAll(String name, String category, Pageable pageable) {
        Page<Book> page;

        if ((name == null || name.trim().length() == 0) && (category == null || category.trim().length() == 0)) {
            page = repository.findAll(pageable);
        } else if ((name != null && name.trim().length() > 0) && category.trim().length() == 0) {
            page = repository.findByName(name.trim(), pageable);
        } else if (name == null || name.trim().length() == 0) {
            page = repository.findByCategory(category, pageable);
        } else {
            page = repository.findByNameAndCategory(name.trim(), category.trim(), pageable);
        }

        if (page.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Nenhum livro encontrado.");
        }


        List<Book> books = page.getContent();

        return PageableDTO
                .builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .books(books)
                .build();
    }

    @Override
    public BookDto findById(UUID id) {
        Optional<Book> book = repository.findById(id);

        if(book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book update(UUID id, BookDto request) {
        Optional<Book> book = repository.findById(id);

        if (book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }

        book.get().setCategory(request.getCategory());
        book.get().setUnits(request.getUnits());

        return repository.save(book.get());
    }

    @Override
    public void delete(UUID id) {
        Optional<Book> book = repository.findById(id);

        if (book.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Livro não encontrado!");
        }

        repository.deleteById(id);
    }
}
