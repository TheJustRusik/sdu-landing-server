package org.kenuki.landingserver.services;

import org.kenuki.landingserver.configurations.LandingUserDetails;
import org.kenuki.landingserver.entities.User;
import org.kenuki.landingserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LandingUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public void getUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        return user.map(LandingUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("[" + username + "] not found!"));
    }
}
