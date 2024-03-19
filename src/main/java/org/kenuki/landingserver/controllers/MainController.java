package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.ColdRequestDTO;
import org.kenuki.landingserver.dtos.HotRequestDTO;
import org.kenuki.landingserver.entities.Portfolio;
import org.kenuki.landingserver.entities.Review;
import org.kenuki.landingserver.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
public class MainController {
    private RequestService requestService;
    @GetMapping("/")
    public String dataForAll(){
        return "This data is for all!";
    }
    @PostMapping("/request_hot")
    public ResponseEntity<?> leaveHotRequest(@RequestBody HotRequestDTO hotRequestDTO){
        return requestService.createNewHotRequest(hotRequestDTO);
    }
    @PostMapping("/request_cold")
    public ResponseEntity<?> leaveColdRequest(@RequestBody ColdRequestDTO coldRequestDTO){
        return requestService.createNewColdRequest(coldRequestDTO);
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(){
        return requestService.getAllReviews();
    }
    @GetMapping("/portfolios")
    public ResponseEntity<List<Portfolio>> getPortfolios(){
        return requestService.getAllPortfolios();
    }
    @GetMapping("/images/{image_name}")
    public ResponseEntity<?> getImage(@PathVariable String image_name){
        return requestService.getImage(image_name);
    }
}
