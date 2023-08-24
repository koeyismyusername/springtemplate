package com.github.springtemplate.repository;

import com.github.springtemplate.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRespository extends JpaRepository<Authorization, Integer> {
    Optional<Authorization> findByEmailAndIsDeletedIsFalse(String email);

    boolean existsByEmailAndIsDeletedIsFalse(String email);
}
