package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;
    @GetMapping("/")
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
