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
    @GetMapping("/content")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllContent(){
        return adminService.getAllContent();
    }
    @PostMapping("/content")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createContent(@RequestParam String key, @RequestParam String content){
        return adminService.createTextContent(key, content);
    }
    @PutMapping("/content")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateContent(@RequestParam String key, @RequestParam String new_content){
        return adminService.updateTextContent(key, new_content);
    }
    @DeleteMapping("/content")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteContent(@RequestParam String key){
        return adminService.removeTextContent(key);
    }
}
