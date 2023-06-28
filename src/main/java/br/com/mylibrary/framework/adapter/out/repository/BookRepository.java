package br.com.mylibrary.framework.adapter.out.repository;

import br.com.mylibrary.domain.dto.response.BookResponseDto;
import br.com.mylibrary.domain.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(UUID id);

    Optional<Book> findBookByName(String name);

    default Page<Book> findAllByFilters(String name, String author, String category, Pageable pageable) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Book> example = Example.of(book, matcher);
        return findAll(example, pageable);
    }
}
