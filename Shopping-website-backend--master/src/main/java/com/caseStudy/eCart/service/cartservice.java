package com.caseStudy.eCart.service;
import com.caseStudy.eCart.modals.Cart;
// import com.caseStudy.eCart.modals.FixedCart;
import com.caseStudy.eCart.modals.Orderhistory;
import com.caseStudy.eCart.modals.Products;
import com.caseStudy.eCart.modals.Users;
import com.caseStudy.eCart.Respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class cartservice {
    @Autowired
    private productsRespositry productRepoistory;
    @Autowired
    private cartRepository cartRepository;

    @Autowired
    private userRepository userRepository;
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public Cart addProduct(Long userid, Long productid) {
        Products products = productRepoistory.findByProductid(productid);
        Users users = userRepository.findByUserId((userid));

        if (cartRepository.findByUserAndItems(users, products).isPresent()) {
            Cart cartt =  cartRepository.findByUserAndItems(users, products).get();
            //    FixedCart fixedCart = fixedCartRepository.findByRefId(cartt.getId().intValue());
            cartt.setQuantity(cartt.getQuantity() + 1);
            //  fixedCart.setQuantity(fixedCart.getQuantity() + 1);
            cartRepository.save(cartt);
            // fixedCartRepository.save(fixedCart);
        } else {
            Cart c = new Cart(products, users, 1);
            //   FixedCart fc = new FixedCart(products, users, 1);
            cartRepository.save(c);
            //   fixedCartRepository.save(fc);
        }
        return cartRepository.findByUserAndItems(users, products).get();
    }
    public Optional<Cart> removeproduct(Long userid,Long productid) {
        Products products = productRepoistory.findByProductid(productid);
        Users users = userRepository.findByUserId(userid);

        if(cartRepository.findByUserAndItems(users,products).get().getQuantity() <= 1) {
            Cart cart = cartRepository.findByUserAndItems(users,products).get();
            cart.setQuantity(0);
            cartRepository.delete(cart);
        }
        else {
            Cart cart = cartRepository.findByUserAndItems(users,products).get();

            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.save(cart);
        }
        return cartRepository.findByUserAndItems(users,products);
    }

    public List<Cart> showCart(Long user_id, Principal principal) {
        Users user = userRepository.findByUserId(user_id);
        return cartRepository.findByUserAndItems_Active(user,1);
    }

public Optional<Cart> deleteproduct(Long userid,Long productid)
{
    Products product=productRepoistory.findByProductid(productid);
    Users users=userRepository.findByUserId(userid);
    Cart cart=cartRepository.findByUserAndItems(users,product).get();
    cartRepository.delete(cart);
    return cartRepository.findByUserAndItems(users,product);
}
   public String clearCart(Long userId,Principal principal) {

        Users user = userRepository.findByUserId(userId);
        List<Cart> cartList=cartRepository.findAllByUser(user);
        for (Cart cart : cartList) {
            cartRepository.deleteById(cart.getCartId());
        }
        return "cart cleared!";
    }
    public double checkout(Long userid, Principal principal) {
        Users users = userRepository.findByUserId(userid);
        double p;
        double total = 0;
        List<Cart> cartList = cartRepository.findAllByUser(users);
        for(Cart cart: cartList){
            Orderhistory orderHistory = new Orderhistory();
            orderHistory.setProducts(cart.getItems());
            orderHistory.setUsers(cart.getUser());
            p = cart.getItems().getProductPrice();
            orderHistory.setQuantity(cart.getQuantity());
            total = total+cart.getQuantity()*p;
            orderHistory.setPrice((int)(cart.getQuantity()*p));
            orderHistory.setDate();
            orderHistoryRepository.save(orderHistory);

        }
        clearCart(userid,principal);
        return total;
    }
    public List<Orderhistory> showorderhistory(Long userid,Principal principal)
    {
        Users users=userRepository.findByUserId(userid);
        return orderHistoryRepository.findAllByUsers(users);
    }

}


