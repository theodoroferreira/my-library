package br.com.mylibrary.framework.adapter.out.repository;

import br.com.mylibrary.domain.model.Book;
import br.com.mylibrary.domain.model.Borrowing;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    default Page<Borrowing> findAllByFilters(LocalDate borrowingDate, LocalDate returnDate, Pageable pageable) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBorrowingDate(borrowingDate);
        borrowing.setReturnDate(returnDate);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Borrowing> example = Example.of(borrowing, matcher);
        return findAll(example, pageable);
    }
}
