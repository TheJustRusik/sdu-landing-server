package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String dataForAll(){
        return "This data is for all!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAdmin(){
        return "This data is only for admins!";
    }

    @PutMapping("/password")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestParam String new_password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return ResponseEntity.notFound().build();
        }
        String name = authentication.getName();
        Optional<User> _user = userRepository.findByName(name);
        if(_user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = _user.get();
        user.setPassword(passwordEncoder.encode(new_password));
        userRepository.save(user);
        return ResponseEntity.ok("Password changed!");
    }

}
