package com.github.springtemplate.security;

import com.github.springtemplate.entity.Authorization;
import com.github.springtemplate.exception.errorcode.JwtErrorCode;
import com.github.springtemplate.repository.AuthorityRepository;
import com.github.springtemplate.repository.AuthorizationRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationDetailsService implements UserDetailsService {

    private final AuthorizationRespository authorizationRepository;
    private final AuthorityRepository authorityRespository;

    @Override
    public AuthorizationDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Authorization authorization = authorizationRepository.findByEmailAndIsDeletedIsFalse(email).orElseThrow(JwtErrorCode.UNRELIABLE_JWT::exception);
        String jwt = authorization.getToken();
        Set<SimpleGrantedAuthority> authorities = authorityRespository.findAllByUserEmailAndIsDeletedIsFalse(email).stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().getName()))
                .collect(Collectors.toSet());

        return AuthorizationDetails.builder()
                .email(email)
                .jwt(jwt)
                .authorities(authorities)
                .build();
    }
}
