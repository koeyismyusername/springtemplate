package com.github.springtemplate.security;

import com.github.springtemplate.exception.errorcode.TestErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final ProviderManager providerManager;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtProvider.HEADER_NAME);

        if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
            Authentication beforeAuthenticate = jwtProvider.parseAuthentication(jwt);
            Authentication afterAuthenticate = providerManager.authenticate(beforeAuthenticate);
            SecurityContextHolder.getContext().setAuthentication(afterAuthenticate);
        }

        doFilter(request, response, filterChain);
    }
}
