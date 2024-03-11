package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> updatePassword(String password, Authentication authentication){
        if(password.length() < 16){
            return ResponseEntity.badRequest().body("Password length < 16");
        }
        if(!authentication.isAuthenticated()){
            return ResponseEntity.notFound().build();
        }
        String name = authentication.getName();
        Optional<User> _user = userRepository.findByName(name);
        if(_user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = _user.get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok("Password changed!");
    }
}
