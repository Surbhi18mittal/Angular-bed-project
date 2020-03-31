package com.caseStudy.eCart.controller;

import com.caseStudy.eCart.Respository.productsRespositry;
import com.caseStudy.eCart.modals.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class productsController {
@Autowired
productsRespositry p;

@GetMapping("/notes")
public List<Products> getAllNotes()
{
    return p.findAll();
}
@PostMapping("/notes1")
    public Products createNode(@Valid @RequestBody Products p1)
{
    return p.save(p1);
}
@GetMapping("/notes/{id}")
    public Products getByid(@PathVariable(value="id")Long noteId)

{
    return p.findByProductid(noteId);
}
@GetMapping("/products/category/{category}")
    public List<Products> getnodebyCategory(@PathVariable(value="category")String category)
{
    return p.findByCategory(category);
}


@GetMapping("/getByPrice/{price1}/{price2}")
    public List<Products> getProductByPrice(@PathVariable(value="price1")int  p_price1,@PathVariable(value="price2")int p_price2)
{
    return p.findAllByProductPriceBetween(p_price1,p_price2);

}
@PostMapping("/create")
    public Products creater(@Valid @RequestBody Products u)
{
    return p.save(u);
}
    @PutMapping("/edit/{id}")
    public Products updateNote(@PathVariable(value = "id") Long noteId,@Valid @RequestBody Products noteDetails) {
        Products x = p.findByProductid(noteId);
        x.setProductName(noteDetails.getProductName());
        x.setCategory(noteDetails.getCategory());
        x.setDetail(noteDetails.getDetail());
        x.setImage(noteDetails.getImage());
        x.setProductPrice(noteDetails.getProductPrice());
        x.setActive(noteDetails.getActive());
        Products updatedNote = p.save(x);
        return updatedNote;
    }
//    @GetMapping("/products/{category}/{p1}/{p2}")
//    public List<Products> getNodeByPriceRange(@PathVariable(value="category")String category,@PathVariable(value="p1")String p1,@PathVariable(value="p2")String p2)
//    {
//        return p.findAllByCategoryAndPriceBetween(category,p1,p2);
//    }
}
