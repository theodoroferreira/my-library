package br.com.mylibrary.framework.adapter.out.repository;

import br.com.mylibrary.domain.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {


}
