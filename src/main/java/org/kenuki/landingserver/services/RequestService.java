package org.kenuki.landingserver.services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.kenuki.landingserver.dtos.ColdRequestDTO;
import org.kenuki.landingserver.dtos.HotRequestDTO;
import org.kenuki.landingserver.entities.LandingType;
import org.kenuki.landingserver.entities.Order;
import org.kenuki.landingserver.entities.Portfolio;
import org.kenuki.landingserver.entities.Review;
import org.kenuki.landingserver.exceptions.ImageNotFoundException;
import org.kenuki.landingserver.messages.DefaultMessages;
import org.kenuki.landingserver.repositories.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {
    private OrdersRepository ordersRepository;
    private LandingTypeRepository landingTypeRepository;
    private ReviewRepository reviewRepository;
    private PortfolioRepository portfolioRepository;
    private ImageServices imageServices;
    private ContactRepository contactRepository;
    public ResponseEntity<String> createNewHotRequest(HotRequestDTO hotRequestDTO){
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
        Order request = new Order();
        request.setName(hotRequestDTO.getName());
        request.setEmail(hotRequestDTO.getEmail());
        request.setPhone(PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        request.setLandingType(landingType.get());
        request.setChecked(false);
        request.setStatus(0);
        ordersRepository.save(request);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<String> createNewColdRequest(ColdRequestDTO coldRequestDTO){
        if(coldRequestDTO.getName().length() < 2)
            return ResponseEntity.badRequest().body(DefaultMessages.wrongName);
        PhoneNumber phoneNumber;
        try {
            phoneNumber = PhoneNumberUtil.getInstance().parse(coldRequestDTO.getPhone_number(), "KZ");
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.wrongPhone);
        }
        Order order = new Order();
        order.setName(coldRequestDTO.getName());
        order.setChecked(false);
        order.setPhone(PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        order.setStatus(0);
        ordersRepository.save(order);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewRepository.findAllByVisibleIsTrue());
    }
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        return ResponseEntity.ok(portfolioRepository.findAll());
    }
    public ResponseEntity<?> getImage(String id){
        Optional<Portfolio> portfolio = portfolioRepository.findByImage(id);
        if(portfolio.isEmpty()){
            return ResponseEntity.badRequest().body(DefaultMessages.imageNotFound);
        }
        try {
            Resource image = imageServices.loadImage(portfolio.get().getImage());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + image.getFilename() + "\"")
                    .body(image);

        }catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.badImage);
        } catch (ImageNotFoundException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.imageNotFound);
        }

    }


    public ResponseEntity<String> getContact(String key) {
        if(contactRepository.existsById(key))
            return ResponseEntity.ok(contactRepository.findById(key).get().getValue());
        return ResponseEntity.notFound().build();
    }
}
