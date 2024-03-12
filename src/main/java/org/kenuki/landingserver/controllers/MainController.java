package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.RequestDTO;
import org.kenuki.landingserver.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private RequestService requestService;
    @GetMapping("/")
    public String dataForAll(){
        return "This data is for all!";
    }

    @PostMapping("/request")
    public ResponseEntity<?> leaveRequest(@RequestBody RequestDTO requestDTO){
        return requestService.createNewRequest(requestDTO);
    }

    @GetMapping("/content")
    public ResponseEntity<?> getTextContent(@RequestParam String key){
        return requestService.getTextContent(key);
    }



}
