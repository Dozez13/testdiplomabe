package com.example.projectbe.web.security.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projectbe.core.service.UserService;
import com.example.projectbe.domain.entity.User;
import com.example.projectbe.web.security.model.UserAuthenticationInfo;
import com.example.projectbe.web.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("jwtProvider")
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawToken = (String) authentication.getCredentials();

        DecodedJWT decodedJWT = jwtUtil.decodeRawJWTToken(rawToken);

        String email = decodedJWT.getSubject();

        User userToAuthenticate = Optional.ofNullable(userService.findUserByEmail(email)).orElseThrow(() -> new BadCredentialsException("Email or password is incorrect"));

        List<GrantedAuthority> grantedAuthorities = decodedJWT.getClaim("scopes").asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        String role = decodedJWT.getClaim("role").asString();

        UserAuthenticationInfo authenticationInfo = new UserAuthenticationInfo(email, role, grantedAuthorities);

        return new JWTAuthenticationToken(grantedAuthorities, authenticationInfo);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
