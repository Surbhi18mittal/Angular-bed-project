package com.caseStudy.eCart.controller;


import com.caseStudy.eCart.Respository.productsRespositry;
import com.caseStudy.eCart.Respository.userRepository;
import com.caseStudy.eCart.modals.Products;
import com.caseStudy.eCart.modals.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
public class UserLoginController {
    @Autowired
    userRepository p1;

  //  @GetMapping(path="/showusers" ,produces = "application/json")
    //public ArrayList<Users> showusers()
    //{
      //  return p1.showUser();
    //}

  //  @PostMapping(path="/user",produces="application/json")
    //public String signup(@Valid @RequestBody Users users)
    //{
      //  users.setIsActive(1);
       // p1.add(users);
        //return "\"success\"";
    //}
    @GetMapping("/validateUser")
    public String validateuser()
    {
        return "\"user validated\"";
    }
    @PostMapping("/addUsers")
    public Users addusers(@Valid @RequestBody Users user )
    {
        return p1.save(user);
    }
    @GetMapping("/getUsers")
    public List<Users> getusers()
    {
        return p1.findAll();
    }


    //@GetMapping("/getusers/category/{category}")
    //public List<Users> getnodebyCategory(@PathVariable(value="category")String category)
    //{
      //  return p1.findByCategory(category);
    //}

   // @GetMapping("/getByPrice/{price1}/bewteen/{price2}")
    //public List<Users> getProductByPrice(@PathVariable(value="price1")int  p_price1,@PathVariable(value="price2")int p_price2)
    //{
      //  return p1.findAllByProductPriceBetween(p_price1,p_price2);

    //}
//    @GetMapping("/products/{category}/{p1}/{p2}")
//    public List<Products> getNodeByPriceRange(@PathVariable(value="category")String category,@PathVariable(value="p1")String p1,@PathVariable(value="p2")String p2)
//    {
//        return p.findAllByCategoryAndPriceBetween(category,p1,p2);
//    }
}


