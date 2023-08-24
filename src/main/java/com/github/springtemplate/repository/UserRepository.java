package com.github.springtemplate.repository;

import com.github.springtemplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailAndIsDeletedIsFalse(String email);

    Optional<User> findByEmailAndIsDeletedIsFalse(String email);
}
