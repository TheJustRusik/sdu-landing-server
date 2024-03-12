package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.entities.Content;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.ContentRepository;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository userRepository;
    private ContentRepository contentRepository;
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> updatePassword(String password, Authentication authentication){
        if(password.length() < 16){
            return ResponseEntity.badRequest().body("Password length < 16");
        }
        if(!authentication.isAuthenticated()){
            return ResponseEntity.notFound().build();
        }
        String name = authentication.getName();
        Optional<User> _user = userRepository.findByName(name);
        if(_user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = _user.get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok("Password changed!");
    }
    public ResponseEntity<?> updateTextContent(String key, String new_content){
        Optional<Content> optionalContent = contentRepository.findById(key);
        if (optionalContent.isEmpty()){
            return ResponseEntity.badRequest().body("Content not found!");
        }
        Content content = optionalContent.get();
        content.setContent(new_content);
        contentRepository.save(content);
        return ResponseEntity.ok("Success!");
    }
    public ResponseEntity<?> createTextContent(String key, String content){
        if(contentRepository.existsById(key)){
            return ResponseEntity.badRequest().body("Content already exist!");
        }
        Content _content = new Content();
        _content.setId(key);
        _content.setContent(content);
        contentRepository.save(_content);
        return ResponseEntity.ok("Success!");
    }
    public ResponseEntity<?> removeTextContent(String key){
        if(!contentRepository.existsById(key)){
            return ResponseEntity.badRequest().body("Can't delete non existing content!");
        }
        contentRepository.deleteById(key);
        return ResponseEntity.ok("Success!");
    }
    public ResponseEntity<?> getAllContent(){
        return ResponseEntity.ok(contentRepository.findAll());
    }
}
