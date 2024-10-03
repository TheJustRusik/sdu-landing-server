package org.kenuki.landingserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.ColdRequestDTO;
import org.kenuki.landingserver.dtos.HotRequestDTO;
import org.kenuki.landingserver.entities.Contact;
import org.kenuki.landingserver.entities.Portfolio;
import org.kenuki.landingserver.entities.Review;
import org.kenuki.landingserver.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class MainController {
    private RequestService requestService;
    @GetMapping("/")
    @Operation(summary = "Useless 'Hello, World!' endpoint for user", description = "Its absolutely useless :)")
    public String dataForAll(){
        return "This data is for all!";
    }
    @PostMapping("/request_hot")
    @Operation(summary = "Endpoint for leave HOT request", description = "Provide every field of HotRequestDTO, have own basic validation")
    public ResponseEntity<?> leaveHotRequest(@RequestBody HotRequestDTO hotRequestDTO){
        return requestService.createNewHotRequest(hotRequestDTO);
    }
    @PostMapping("/request_cold")
    @Operation(summary = "Endpoint for leave COLD request", description = "Provide every field of ColdRequestDTO, have own basic validation")
    public ResponseEntity<?> leaveColdRequest(@RequestBody ColdRequestDTO coldRequestDTO){
        return requestService.createNewColdRequest(coldRequestDTO);
    }
    @GetMapping("/reviews")
    @Operation(summary = "Endpoint for get reviews", description = "This will return all VISIBLE reviews, return null if there is no VISIBLE review")
    public ResponseEntity<List<Review>> getReviews(){
        return requestService.getAllReviews();
    }
    @GetMapping("/portfolios")
    @Operation(summary = "Endpoint for get all portfolios", description = "This will return only STRING variables, even for image! To get image, use another endpoint")
    public ResponseEntity<List<Portfolio>> getPortfolios(){
        return requestService.getAllPortfolios();
    }
    @GetMapping("/images/{image_name}")
    @Operation(summary = "Endpoint for get image", description = "From GET(/portfolios), you will get image title, use that title there, to get image. This is for traffic and server optimization!")
    public ResponseEntity<?> getImage(@PathVariable String image_name){
        return requestService.getImage(image_name);
    }
    @GetMapping("/contacts")
    @Operation(summary = "Endpoint to get contact information", description = "You will get: phone, email, address")
    public ResponseEntity<List<Contact>> getContact(){
        return requestService.getContact();
    }
}
