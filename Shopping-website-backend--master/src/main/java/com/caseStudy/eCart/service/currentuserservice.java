package com.caseStudy.eCart.service;

import com.caseStudy.eCart.Respository.userRepository;
import com.caseStudy.eCart.modals.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class currentuserservice {
    @Autowired
    userRepository u;
    public Users getpro(Principal principal)
    {
        Optional<Users> my=u.findByUsername(principal.getName());
        return my.get();
    }
}
