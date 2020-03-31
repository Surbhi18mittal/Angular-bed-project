package com.caseStudy.eCart.Respository;

import com.caseStudy.eCart.modals.Cart;
import com.caseStudy.eCart.modals.Products;
import com.caseStudy.eCart.modals.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface cartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserAndItems(Users users, Products products);

    List<Cart> findAllByUser(Users user);

    List<Cart> findByUserAndItems_Active(Users user,int a);


}
