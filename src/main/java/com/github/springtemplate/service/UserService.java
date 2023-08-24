package com.github.springtemplate.service;

import com.github.springtemplate.dto.request.LoginRequest;
import com.github.springtemplate.dto.request.SignupRequest;
import com.github.springtemplate.dto.response.ApiResponse;
import com.github.springtemplate.dto.response.LoginResponse;
import com.github.springtemplate.dto.response.SignupResponse;
import com.github.springtemplate.dto.response.UserResponse;
import com.github.springtemplate.entity.Authority;
import com.github.springtemplate.entity.Authorization;
import com.github.springtemplate.entity.Role;
import com.github.springtemplate.entity.User;
import com.github.springtemplate.exception.errorcode.UserErrorCode;
import com.github.springtemplate.repository.AuthorityRepository;
import com.github.springtemplate.repository.AuthorizationRespository;
import com.github.springtemplate.repository.UserRepository;
import com.github.springtemplate.security.JwtProvider;
import com.github.springtemplate.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorizationRespository authorizationRespository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public ApiResponse<UserResponse> signup(SignupRequest request) {
        String email = request.email();
        if (userRepository.existsByEmailAndIsDeletedIsFalse(email)) throw UserErrorCode.EXISTS_EMAIL.exception();

        String encodedPassword = passwordEncoder.encode(request.password());

        User newData = User.from(request, encodedPassword);
        User savedData = JpaUtils.managedSave(userRepository, newData);
        UserResponse response = UserResponse.from(savedData);
        return ApiResponse.success("회원가입에 성공했습니다.", response);
    }

    public ApiResponse<LoginResponse> login(LoginRequest request) {
        String email = request.email();

        if (authorizationRespository.existsByEmailAndIsDeletedIsFalse(email)) throw UserErrorCode.EXISTS_LOGIN.exception();
        User data = userRepository.findByEmailAndIsDeletedIsFalse(email).orElseThrow(UserErrorCode.INVALID_EMAIL::exception);
        String encodedPassword = data.getPassword();
        String rawPassword = request.password();
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) throw UserErrorCode.INVALID_PASSWORD.exception();

        Set<String> authorities = authorityRepository.findAllByUserEmailAndIsDeletedIsFalse(email).stream()
                .map(Authority::getRole)
                .map(Role::getName)
                .collect(Collectors.toSet());
        Map<String, String> map = jwtProvider.createToken(email, authorities);
        String jwt = map.get("token");
        String refresh = map.get("refresh");
        Authorization newData = Authorization.builder()
                .email(email)
                .token(jwt)
                .refresh(refresh)
                .isDeleted(false)
                .createdAt(new Timestamp(new Date().getTime()))
                .build();
        Authorization savedData = JpaUtils.managedSave(authorizationRespository, newData);
        LoginResponse response = LoginResponse.from(savedData);
        return ApiResponse.success("로그인에 성공했습니다.", response);
    }
}
