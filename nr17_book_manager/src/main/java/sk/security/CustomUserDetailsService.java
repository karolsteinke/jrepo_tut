package sk.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import sk.model.User;
import sk.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    //Return user data (name & password); Spring calls it when "/login" POST request happens
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow( () -> new UsernameNotFoundException("User not found"))
        ;
        
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of() //empty list = no roles assignment
        );
    }
    
}
