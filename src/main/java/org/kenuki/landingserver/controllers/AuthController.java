package org.kenuki.landingserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.LoginDTO;
import org.kenuki.landingserver.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private AuthService authService;
    @PostMapping("/login")
    @Operation(summary = "Login endpoint", description = "If username and password correct, it will send JWT-token, with that token user have access to ALL endpoints")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO){
        return authService.login(loginDTO);
    }

}
