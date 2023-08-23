package com.github.springtemplate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
@Table(name = "authorization")
public class Authorization {

    @Id
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "jwt", nullable = false, length = 600)
    private String jwt;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}
