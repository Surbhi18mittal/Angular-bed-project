package com.caseStudy.eCart.modals;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="cartId")
    private long cartId;
@ManyToOne
    private Products items;
@ManyToOne
    private Users user;
@Column(name="quantity")
    private int quantity;
public Cart(com.caseStudy.eCart.modals.Products items,com.caseStudy.eCart.modals.Users user,int quantity)
{
    this.items=items;
    this.user=user;
    this.quantity=quantity;
}
public Cart()
{

}

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Products getItems() {
        return items;
    }

    public void setItems(Products items) {
        this.items = items;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
