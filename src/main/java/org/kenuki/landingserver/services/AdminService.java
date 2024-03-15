package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.admin.PortfolioDTO;
import org.kenuki.landingserver.entities.Image;
import org.kenuki.landingserver.entities.Portfolio;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.exceptions.BadImagePathException;
import org.kenuki.landingserver.messages.DefaultMessages;
import org.kenuki.landingserver.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository userRepository;
    private ColdRequestRepository coldRequestRepository;
    private HotRequestRepository hotRequestRepository;
    private PasswordEncoder passwordEncoder;
    private ImageServices imageServices;
    private PortfolioRepository portfolioRepository;
    private ImageRepository imageRepository;
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
    public ResponseEntity<?> addPortfolio(PortfolioDTO portfolioDTO){
        try{
            Image image = imageServices.storeImage(portfolioDTO.getImage());
            Portfolio portfolio = new Portfolio();
            portfolio.setDescription(portfolioDTO.getDescription());
            portfolio.setImage(image);
            portfolio.setTitle(portfolioDTO.getTitle());
            portfolioRepository.save(portfolio);
            return ResponseEntity.ok(DefaultMessages.success);
        }catch (BadImagePathException | IOException e) {
            return ResponseEntity.badRequest().body(DefaultMessages.cantSaveImage);
        }
    }
    public ResponseEntity<?> deletePortfolio(String title){
        boolean imageDeleted = true;
        Optional<Portfolio> portfolio = portfolioRepository.findById(title);
        if (portfolio.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.portfolioNotFound);
        try {
            imageServices.deleteImage(portfolio.get().getImage().getUrl());
        } catch (IOException e) {
            imageDeleted = false;
        }
        imageRepository.delete(portfolio.get().getImage());
        portfolioRepository.delete(portfolio.get());
        if (!imageDeleted)
            System.out.println("Portfolio deleted but image not, report this incident!\n" +
                               "Portfolio title: " + title + "\n" +
                               "Image: [id: " + portfolio.get().getImage().getId() + ", url: " +portfolio.get().getImage().getUrl() + "]");

        return ResponseEntity.ok(DefaultMessages.success);
    }

}
