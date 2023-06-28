package br.com.mylibrary.framework.adapter.out.repository;

import br.com.mylibrary.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByCpf(String cpf);
}
