package org.kenuki.landingserver.services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.kenuki.landingserver.dtos.RequestDTO;
import org.kenuki.landingserver.entities.Request;
import org.kenuki.landingserver.repositories.RequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository requestRepository;
    public ResponseEntity<?> createNewRequest(RequestDTO requestDTO){
        if(!EmailValidator.getInstance().isValid(requestDTO.getEmail())){
            return ResponseEntity.badRequest().body("Wrong email!");
        }
        PhoneNumber phonenumber;
        try{
            phonenumber = PhoneNumberUtil.getInstance().parse(requestDTO.getPhone_number(), "KZ");
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body("Wrong phone!");
        }
        Request request = new Request();
        request.setName(requestDTO.getName());
        request.setEmail(requestDTO.getEmail());
        request.setPhone(PhoneNumberUtil.getInstance().format(phonenumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        requestRepository.save(request);
        return ResponseEntity.ok("Success!");
    }


}
