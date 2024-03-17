package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.admin.PortfolioDTO;
import org.kenuki.landingserver.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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

    @PostMapping("/portfolio")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createPortfolio(@RequestBody PortfolioDTO portfolioDTO){
        return adminService.addPortfolio(portfolioDTO);
    }
    @DeleteMapping("/portfolio")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletePortfolio(@RequestParam String title){
        return adminService.deletePortfolio(title);
    }
}
