package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.LoginDTO;
import org.kenuki.landingserver.configurations.JwtProvider;
import org.kenuki.landingserver.services.AuthService;
import org.kenuki.landingserver.services.LandingUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO){
        return authService.login(loginDTO);
    }

}
