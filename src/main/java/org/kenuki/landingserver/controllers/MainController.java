package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.RequestDTO;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.kenuki.landingserver.services.AdminService;
import org.kenuki.landingserver.services.RequestService;
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
    private RequestService requestService;
    private AdminService adminService;
    @GetMapping("/")
    public String dataForAll(){
        return "This data is for all!";
    }

    @PostMapping("/request")
    public ResponseEntity<?> leaveRequest(@RequestBody RequestDTO requestDTO){
        return requestService.createNewRequest(requestDTO);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin(){
        return "This data is only for admins!";
    }

    @PutMapping("/password")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestParam String new_password){
        return adminService.updatePassword(new_password, SecurityContextHolder.getContext().getAuthentication());
    }

}
