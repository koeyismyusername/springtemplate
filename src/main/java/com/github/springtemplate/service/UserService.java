package com.github.springtemplate.service;

import com.github.springtemplate.dto.request.SignupRequest;
import com.github.springtemplate.dto.response.ApiResponse;
import com.github.springtemplate.dto.response.SignupResponse;
import com.github.springtemplate.dto.response.UserResponse;
import com.github.springtemplate.entity.User;
import com.github.springtemplate.exception.errorcode.UserErrorCode;
import com.github.springtemplate.repository.UserRepository;
import com.github.springtemplate.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiResponse<UserResponse> signup(SignupRequest request) {
        String email = request.email();
        if (userRepository.existsByEmailAndIsDeletedIsFalse(email)) throw UserErrorCode.EXISTS_EMAIL.exception();

        String encodedPassword = passwordEncoder.encode(request.password());

        User newData = User.from(request, encodedPassword);
        User savedData = JpaUtils.managedSave(userRepository, newData);
        UserResponse response = UserResponse.from(savedData);
        return ApiResponse.success("회원가입에 성공했습니다.", response);
    }

}
