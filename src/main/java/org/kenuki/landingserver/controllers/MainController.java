package org.kenuki.landingserver.controllers;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.ColdRequestDTO;
import org.kenuki.landingserver.dtos.HotRequestDTO;
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
    @PostMapping("/request_hot")
    public ResponseEntity<?> leaveHotRequest(@RequestBody HotRequestDTO hotRequestDTO){
        return requestService.createNewHotRequest(hotRequestDTO);
    }
    @PostMapping("/request_cold")
    public ResponseEntity<?> leaveColdRequest(@RequestBody ColdRequestDTO coldRequestDTO){
        return requestService.createNewColdRequest(coldRequestDTO);
    }



}
