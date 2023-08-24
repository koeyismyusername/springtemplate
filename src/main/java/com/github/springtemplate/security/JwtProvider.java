package com.github.springtemplate.security;

import com.github.springtemplate.exception.errorcode.JwtErrorCode;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private final SecretKey secretKey;
    private final AuthorizationDetailsService authorizationDetailsService;
    public static final String HEADER_NAME = HttpHeaders.AUTHORIZATION;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String jwt = (String) authentication.getCredentials();
        AuthorizationDetails details = authorizationDetailsService.loadUserByUsername(email);

        if (!details.getPassword().equals(jwt)) throw new AuthenticationException("JWT 토큰 인증에 실패했습니다.") {};

        return new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword(), details.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean validateJwtToken(String jwt) {
        try {
            parseClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            throw JwtErrorCode.EXPIRED_JWT.exception();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw JwtErrorCode.INVALID_SIGNATURE.exception();
        } catch (IllegalArgumentException e) {
            throw JwtErrorCode.EMPTY_JWT.exception();
        }
    }

    public UsernamePasswordAuthenticationToken parseAuthentication(String jwt) {
        Claims claims = parseClaims(jwt);
        String email = claims.getSubject();
        Object authoritiesObj = claims.get("authorities");
        if (!(authoritiesObj instanceof Set<?>)) throw JwtErrorCode.INVALID_SIGNATURE.exception();
        Set<SimpleGrantedAuthority> authorities =((Set<?>) authoritiesObj).stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(email, jwt, authorities);
    }

    public String createToken(String email, Set<String> authorities) {
        Date now = new Date();
        final long duration = 3_600_000L;
        return Jwts.builder()
                .setSubject(email)
                .claim("authorities", authorities)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + duration))
                .signWith(secretKey)
                .compact();
    }

    public String parseJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_NAME);
        if (bearerToken == null) throw JwtErrorCode.EMPTY_JWT.exception();
        if (!bearerToken.startsWith("Bearer ")) throw JwtErrorCode.INVALID_SIGNATURE.exception();
        return bearerToken.substring("Bearer ".length());
    }
}
