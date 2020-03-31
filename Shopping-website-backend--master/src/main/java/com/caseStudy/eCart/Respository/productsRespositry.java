package com.caseStudy.eCart.Respository;

import com.caseStudy.eCart.modals.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productsRespositry extends JpaRepository<Products ,Long> {

    List<Products> findAllByProductPriceBetween(int p_price1,int p_price2);
    //List<Products> findAllByCategoryAndPriceBetween(String category, String p1, String p2);
    List<Products> findByCategory(String category);


    Products findByProductid(Long productid);
}
