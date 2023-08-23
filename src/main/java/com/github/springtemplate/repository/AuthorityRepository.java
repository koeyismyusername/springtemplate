package com.github.springtemplate.repository;

import com.github.springtemplate.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Set<Authority> findAllByUserEmailAndDeletedIsFalse(String email);
}
