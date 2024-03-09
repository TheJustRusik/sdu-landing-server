package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
//@RequestMapping("/admin")
//@AllArgsConstructor
//public class AdminController {
////    private UserRepository userRepository;
////    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
////    @GetMapping("/")
////    public String getAdmin(){
////        return "This data is only for admins!";
////    }
////
////    @PutMapping("/{id}/password")
////    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestParam String newPassword){
////        Optional<User> optionalUser = userRepository.findById(id);
////        if (optionalUser.isPresent()) {
////            User user = optionalUser.get();
////            user.setPassword(newPassword);
////            userRepository.save(user);
////            return ResponseEntity.ok("Password updated successfully");
////        }else {
////            return ResponseEntity.notFound().build();
////        }
////    }
//}
