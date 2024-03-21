package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.configurations.JwtProvider;
import org.kenuki.landingserver.dtos.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
