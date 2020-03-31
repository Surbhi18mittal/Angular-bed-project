package com.caseStudy.eCart.controller;

import com.caseStudy.eCart.Respository.userRepository;
import com.caseStudy.eCart.modals.Users;
import com.caseStudy.eCart.service.currentuserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/profile")
public class myprofile {
@Autowired
    currentuserservice c;
@Autowired
userRepository u;
@GetMapping("/get")
    public Users getdata(Principal principal)
{
    return c.getpro(principal);
}
@PutMapping("/update")
    public Users update(@Valid @RequestBody Users users)
{
    users.setActive(1);
    u.save(users);
    return users;
}
}
