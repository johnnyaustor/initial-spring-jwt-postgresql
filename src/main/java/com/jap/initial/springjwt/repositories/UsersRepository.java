package com.jap.initial.springjwt.repositories;

import com.jap.initial.springjwt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users getById(Long id);
}
