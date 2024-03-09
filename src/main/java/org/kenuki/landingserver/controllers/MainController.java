package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    @GetMapping("/")
    public String dataForAll(){
        return "This data is for all!";
    }
    private UserRepository userRepository;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin(){
        return "This data is only for admins!";
    }

    @PutMapping("/{id}/password")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestParam String newPassword){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return ResponseEntity.ok("Password updated successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
