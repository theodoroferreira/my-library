package br.com.mylibrary.framework.adapter.out.repository;

import br.com.mylibrary.domain.model.Book;
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

    Page<Book> findByName(String name, Pageable pageable);

    Optional<Book> findBookByName(String name);

    Page<Book> findByCategory(String category, Pageable pageable);

    Page<Book> findByNameAndCategory(String name, String category, Pageable pageable);
}
