package br.com.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.backend.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
