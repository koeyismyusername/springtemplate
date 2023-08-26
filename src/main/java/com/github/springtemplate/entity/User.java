package com.github.springtemplate.entity;

import com.github.springtemplate.dto.request.SignupRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends CommonEntity{

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

    public static User from(SignupRequest request, String encodedPassword) {
        return User.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .build();
    }
}
