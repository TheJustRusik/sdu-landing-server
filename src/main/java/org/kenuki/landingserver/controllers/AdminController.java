package org.kenuki.landingserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.PortfolioDTO;
import org.kenuki.landingserver.dtos.ReviewDTO;
import org.kenuki.landingserver.entities.Contact;
import org.kenuki.landingserver.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;
    @Operation(summary = "Admin`s 'Hello, World!'", description = "Useless endpoint :)")
    @GetMapping("/")
    public String getAdmin(){
        return "This data is only for admins!";
    }
    //Password
    @PutMapping("/password")
    @Operation(summary = "Change password", description = "This endpoint helps to change password (No any password verification, so be careful!")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestParam String new_password){
        return adminService.updatePassword(new_password, SecurityContextHolder.getContext().getAuthentication());
    }
    //Portfolios
    @GetMapping(path = "/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get specific portfolio", description = "Can give specific portfolio, with id = {id}")
    public ResponseEntity<?> getPortfolio(@PathVariable Long id){
        return adminService.getPortfolio(id);
    }
    @PostMapping(path = "/portfolio")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Add 1 portfolio", description = "Every portfolio should have unique image, and every image should have unique name! EXAMPLE: You can't assign lol.png for more than 1 portfolio!")
    public ResponseEntity<?> addPortfolio(@ModelAttribute PortfolioDTO portfolioDTO){
        return adminService.addPortfolio(portfolioDTO);
    }
    @PutMapping(path = "/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update specific portfolio", description = "Update is actually recreating, so every field in PortfolioDTO == update for that Portfolio, so dont forget to provide every field! WARNING: Every portfolio should have unique image, and every image should have unique name! EXAMPLE: if some existing portfolio use lol.png, you can't update portfolios picture for lol.png")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long id, @ModelAttribute PortfolioDTO portfolioDTO){
        return adminService.updatePortfolio(id, portfolioDTO);
    }
    @DeleteMapping("/portfolio/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Deleting portfolio by id", description = "It will delete portfolio and image also!")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long id){
        return adminService.deletePortfolio(id);
    }
    //Reviews
    @GetMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get specific review", description = "Can give specific review, with id = {id}")
    public ResponseEntity<?> getReview(@PathVariable Long id){
        return adminService.getReview(id);
    }
    @PostMapping("/review")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Create new portfolio", description = "Provide ReviewDTO for that")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        return adminService.addReview(reviewDTO);
    }
    @PutMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update {id} portfolio", description = "Every field in ReviewDTO == updated Review, so dont forget to assign every field inside ReviewDTO, even if you want change only 1 field, just change that field inside DTO, and provide to other field old Review's value to save it!")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        return adminService.updateReview(id, reviewDTO);
    }
    @DeleteMapping("/review/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Deleting review", description = "This will delete review by {id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id){
        return adminService.deleteReview(id);
    }
    //Orders
    @GetMapping("/order")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get order", description = "This will give list of all orders")
    public ResponseEntity<?> getOrders() {
        return adminService.getOrders();
    }
    @PutMapping("/order/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update order's status", description = "This method only updates order`s status_code and always set 'checked' to true")
    public ResponseEntity<String> setOrderStatus(@PathVariable Long id, @RequestParam Integer status_code){
        return adminService.updateOrder(id, status_code);
    }
    @PatchMapping("/order/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Check order", description = "Set order's checked to true")
    public ResponseEntity<String> checkOrder(@PathVariable Long id) {
        return adminService.updateOrder(id, null);
    }
    @DeleteMapping("/order/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete order", description = "Delete order by id")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        return adminService.deleteOrder(id);
    }
    //Contacts
    @GetMapping("/contact")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get all contacts", description = "This will give list of all contacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        return adminService.getAllContacts();
    }
    @PutMapping("/contact/{key}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update contact by {key}", description = "Update contact by {key} to new value")
    public ResponseEntity<String> updateContact(@PathVariable String key, @RequestParam String new_value){
        return adminService.updateContact(key, new_value);
    }
}
