package org.kenuki.landingserver.services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.kenuki.landingserver.dtos.ColdRequestDTO;
import org.kenuki.landingserver.dtos.HotRequestDTO;
import org.kenuki.landingserver.entities.ColdRequest;
import org.kenuki.landingserver.entities.HotRequest;
import org.kenuki.landingserver.entities.Image;
import org.kenuki.landingserver.entities.LandingType;
import org.kenuki.landingserver.exceptions.ImageNotFoundException;
import org.kenuki.landingserver.messages.DefaultMessages;
import org.kenuki.landingserver.repositories.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {
    private HotRequestRepository hotRequestRepository;
    private ColdRequestRepository coldRequestRepository;
    private LandingTypeRepository landingTypeRepository;
    private ReviewRepository reviewRepository;
    private PortfolioRepository portfolioRepository;
    private ImageServices imageServices;
    private ImageRepository imageRepository;
    public ResponseEntity<?> createNewHotRequest(HotRequestDTO hotRequestDTO){
        if(!EmailValidator.getInstance().isValid(hotRequestDTO.getEmail())){
            return ResponseEntity.badRequest().body(DefaultMessages.wrongMail);
        }
        if(hotRequestDTO.getName().length() < 2){
            return ResponseEntity.badRequest().body(DefaultMessages.wrongName);
        }
        PhoneNumber phoneNumber;
        try{
            phoneNumber = PhoneNumberUtil.getInstance().parse(hotRequestDTO.getPhone_number(), "KZ");
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.wrongPhone);
        }
        Optional<LandingType> landingType = landingTypeRepository.findById(hotRequestDTO.getLanding_type());
        if(landingType.isEmpty()){
            return ResponseEntity.badRequest().body(DefaultMessages.wrongLandingType);
        }
        HotRequest request = new HotRequest();
        request.setName(hotRequestDTO.getName());
        request.setEmail(hotRequestDTO.getEmail());
        request.setPhone(PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        request.setLandingType(landingType.get());
        hotRequestRepository.save(request);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<?> createNewColdRequest(ColdRequestDTO coldRequestDTO){
        if(coldRequestDTO.getName().length() < 2)
            return ResponseEntity.badRequest().body(DefaultMessages.wrongName);
        PhoneNumber phoneNumber;
        try {
            phoneNumber = PhoneNumberUtil.getInstance().parse(coldRequestDTO.getPhone(), "KZ");
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.wrongPhone);
        }
        ColdRequest coldRequest = new ColdRequest();
        coldRequest.setName(coldRequestDTO.getName());
        coldRequest.setPhone(PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        coldRequestRepository.save(coldRequest);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok(reviewRepository.findAll());
    }

    public ResponseEntity<?> getAllPortfolios() {
        return ResponseEntity.ok(portfolioRepository.findAll());
    }
    public ResponseEntity<?> getImage(Long id){
        Optional<Image> optionalImage = imageRepository.findById(id);
        if(optionalImage.isEmpty()){
            return ResponseEntity.badRequest().body(DefaultMessages.imageNotFound);
        }
        try {
            Resource image = imageServices.loadImage(optionalImage.get().getUrl());
            return ResponseEntity.ok(image);
        }catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.badImage);
        } catch (ImageNotFoundException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.imageNotFound);
        }

    }
}
