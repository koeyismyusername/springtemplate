package com.github.springtemplate.entity;

import com.github.springtemplate.dto.request.SignupRequest;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 600)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public static User from(SignupRequest request, String encodedPassword) {
        return User.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .createdAt(new Timestamp(new Date().getTime()))
                .isDeleted(false)
                .build();
    }
}
