package com.caseStudy.eCart.service;

import com.caseStudy.eCart.modals.Users;
import com.caseStudy.eCart.Respository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class userservice {
    @Autowired
    private userRepository userRepository;
    public Optional<Users> CurrentUser(Principal prinicipal) {
        String username = prinicipal.getName();
        return userRepository.findByUsername(username);
    }
    public Long getUserId(Principal principal)
    {
        String username = principal.getName();
        Long id = userRepository.findByUsername(username).get().getUserId();
        return id;
    }
    //    public Long getUserRole(Principal principal) {
//        return userRepository.findByUsername(principal.getName()).get().getRole().getRoleId();
//    }
    public Optional<Users> getUserProfile(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }
    //public ResponseEntity<?> checkDetails(Users user, Principal principal) {
      //  Optional<Users> usercheck = userRepository.findByUsername(principal.getName());
        //Optional<Users> usercheckinfo = userRepository.findByUsername(user.getUsername());
        //if(usercheckinfo.isPresent()& usercheckinfo.get().getUsername()!=usercheck.get().getUsername());
        //HttpHeaders responseHeaders = new HttpHeaders();

    }
//}

