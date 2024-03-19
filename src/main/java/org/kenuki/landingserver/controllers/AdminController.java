package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.PortfolioDTO;
import org.kenuki.landingserver.dtos.ReviewDTO;
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
    //Password
    @PutMapping("/password")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestParam String new_password){
        return adminService.updatePassword(new_password, SecurityContextHolder.getContext().getAuthentication());
    }
    //Portfolio
    @GetMapping(path = "/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getPortfolio(@PathVariable Long id){
        return adminService.getPortfolio(id);
    }
    @PostMapping(path = "/portfolio")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createPortfolio(@ModelAttribute PortfolioDTO portfolioDTO){
        return adminService.addPortfolio(portfolioDTO);
    }
    @PutMapping(path = "/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long id, @ModelAttribute PortfolioDTO portfolioDTO){
        return adminService.updatePortfolio(id, portfolioDTO);
    }
    @DeleteMapping("/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long id){
        return adminService.deletePortfolio(id);
    }
    //Reviews
    @GetMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getReview(@PathVariable Long id){
        return adminService.getReview(id);
    }
    @PostMapping("/review")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        return adminService.addReview(reviewDTO);
    }
    @PutMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        return adminService.updateReview(id, reviewDTO);
    }
    @DeleteMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteReview(@PathVariable Long id){
        return adminService.deleteReview(id);
    }

}
