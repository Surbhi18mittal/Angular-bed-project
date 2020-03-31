package com.caseStudy.eCart.controller;

import com.caseStudy.eCart.Respository.cartRepository;
import com.caseStudy.eCart.modals.Cart;
import com.caseStudy.eCart.modals.Orderhistory;
import com.caseStudy.eCart.service.cartservice;
import com.caseStudy.eCart.service.userservice;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.caseStudy.eCart.service.cartservice;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RestController
@RequestMapping("/cart")
public class cartController {
    @Autowired
    private cartRepository c;
@Autowired
    private cartservice c1;
@Autowired
    private userservice u;
     @Autowired
     public cartController(cartservice c1,userservice u)
     {
         this.c1=c1;
         this.u=u;
     }
    @RequestMapping(value="/removeproduct/receive/{productid}",method = RequestMethod.GET)
    @ResponseBody
    public Optional<Cart> removeproduct(@PathVariable Long productid, Principal principal)
    {
        return c1.removeproduct(u.getUserId(principal),productid);


    }
    @RequestMapping(value="/deleteproduct/receive/{productid}",method = RequestMethod.GET)
    @ResponseBody
    public Optional<Cart> deleteproduct(@PathVariable Long productid, Principal principal)
    {
        return c1.deleteproduct(u.getUserId(principal),productid);


    }
    @RequestMapping(value = "/checkout/recieve", method = RequestMethod.GET)
    @ResponseBody
    public double checkout(Principal principal) {
        return c1.checkout(u.getUserId(principal),principal);
    }


    @RequestMapping(value="/addproduct/receive/{productid}",method = RequestMethod.GET)
    @ResponseBody
    public Cart addproduct(@PathVariable Long productid, Principal principal)
    {
        return c1.addProduct(u.getUserId(principal),productid);


    }
    @RequestMapping(value="/showcart/receive",method = RequestMethod.GET)
    @ResponseBody
    public List<Cart> showcart(Principal principal)
    {
        return c1.showCart(u.getUserId(principal),principal);


    }
    @RequestMapping(value="/orderhistory/receive",method = RequestMethod.GET)
    @ResponseBody
    public List<Orderhistory> history(Principal principal)
    {
        return c1.showorderhistory(u.getUserId(principal),principal);


    }
   @RequestMapping(value="/clearcart",method = RequestMethod.GET)
    @ResponseBody
    public String clearcart(Principal principal)
    {
        return c1.clearCart(u.getUserId(principal),principal);


    }


}
