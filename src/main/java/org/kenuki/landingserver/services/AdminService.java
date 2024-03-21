package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.dtos.PortfolioDTO;
import org.kenuki.landingserver.dtos.ReviewDTO;
import org.kenuki.landingserver.entities.*;
import org.kenuki.landingserver.exceptions.BadImagePathException;
import org.kenuki.landingserver.messages.DefaultMessages;
import org.kenuki.landingserver.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private PasswordEncoder passwordEncoder;
    private ImageServices imageServices;
    private PortfolioRepository portfolioRepository;
    private OrdersRepository ordersRepository;
    private ContactRepository contactRepository;
    //Password
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
    //Portfolios
    public ResponseEntity<?> addPortfolio(PortfolioDTO portfolioDTO){
        if(portfolioRepository.existsByImage(portfolioDTO.getImage().getOriginalFilename())){
            return ResponseEntity.badRequest().body(DefaultMessages.imageTitleAlreadyExist);
        }
        try{
            Portfolio portfolio = new Portfolio();

            portfolio.setDescription(portfolioDTO.getDescription());
            portfolio.setImage(imageServices.storeImage(portfolioDTO.getImage()));
            portfolio.setTitle(portfolioDTO.getTitle());

            portfolioRepository.save(portfolio);

            return ResponseEntity.ok(DefaultMessages.success);
        }catch (BadImagePathException | IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(DefaultMessages.cantSaveImage);
        }
    }
    public ResponseEntity<?> deletePortfolio(Long id){
        boolean imageDeleted = true;
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if (portfolio.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.portfolioNotFound);
        try {
            imageServices.deleteImage(portfolio.get().getImage());
        } catch (IOException e) {
            imageDeleted = false;
        }
        portfolioRepository.delete(portfolio.get());
        if (!imageDeleted)
            System.out.println("Portfolio deleted but image not, report this incident!\n" +
                               "Portfolio title: " + portfolio.get().getTitle() + "\n" +
                               "Image: [url: " +portfolio.get().getImage() + "]");

        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<?> updatePortfolio(Long id, PortfolioDTO portfolioDTO) {
        //Плохие случаи:
        //Обновление несуществующего портфолио
        //При обновлении: присвоение уже существующей картинки

        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if(portfolio.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.portfolioNotFound);

        Portfolio newPortfolio = portfolio.get();
        try {
            newPortfolio.setTitle(portfolioDTO.getTitle());
            newPortfolio.setDescription(portfolioDTO.getDescription());

            Optional<Portfolio> tmp = portfolioRepository.findByImage(portfolioDTO.getImage().getOriginalFilename());
            if(tmp.isPresent()){
                if (!Objects.equals(tmp.get().getId(), id))
                    return ResponseEntity.badRequest().body(DefaultMessages.imageTitleAlreadyExist);
            }

            imageServices.deleteImage(portfolio.get().getImage());
            newPortfolio.setImage(imageServices.storeImage(portfolioDTO.getImage()));

            portfolioRepository.save(newPortfolio);

            return ResponseEntity.ok(DefaultMessages.success);
        }catch (BadImagePathException | IOException e) {
            return ResponseEntity.ok(DefaultMessages.cantSaveImage);
        }


    }
    public ResponseEntity<?> getPortfolio(Long id) {
        return ResponseEntity.ok(portfolioRepository.findById(id));
    }
    //Reviews
    public ResponseEntity<?> getReview(Long id) {
        return ResponseEntity.ok(reviewRepository.findById(id));
    }
    public ResponseEntity<?> addReview(ReviewDTO reviewDTO) {
        reviewRepository.save(new Review(reviewDTO.getAuthor(), reviewDTO.getReview(), reviewDTO.getVisible()));
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<?> updateReview(Long id, ReviewDTO reviewDTO) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.reviewNotFound);
        Review newReview = review.get();
        newReview.setAuthor(reviewDTO.getAuthor());
        newReview.setReview(reviewDTO.getReview());
        newReview.setVisible(reviewDTO.getVisible());
        reviewRepository.save(newReview);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<?> deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.reviewNotFound);
        else
            reviewRepository.delete(review.get());
        return ResponseEntity.ok(DefaultMessages.success);
    }
    //Orders
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(ordersRepository.findAll());
    }
    public ResponseEntity<String> updateOrder(Long id, Integer status_code) {
        Optional<Order> order = ordersRepository.findById(id);
        if(order.isEmpty())
            return ResponseEntity.badRequest().body(DefaultMessages.orderNotFound);
        Order newOrder = order.get();
        newOrder.setChecked(true);
        if (status_code != null)
            newOrder.setStatus(status_code);
        ordersRepository.save(newOrder);
        return ResponseEntity.ok(DefaultMessages.success);
    }
    public ResponseEntity<String> deleteOrder(Long id) {
        if(ordersRepository.existsById(id)){
            ordersRepository.deleteById(id);
            return ResponseEntity.ok(DefaultMessages.success);
        }
        return ResponseEntity.badRequest().body(DefaultMessages.orderNotFound);
    }

    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }
    public ResponseEntity<String> updateContact(String key, String newValue) {
        Optional<Contact> contact = contactRepository.findById(key);
        if(contact.isEmpty())
            return ResponseEntity.notFound().build();
        Contact newContact = contact.get();
        newContact.setValue(newValue);
        contactRepository.save(newContact);
        return ResponseEntity.ok(DefaultMessages.success);
    }
}
