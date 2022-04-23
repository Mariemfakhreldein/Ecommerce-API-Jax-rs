package gov.iti.jets.domain.models;

import jakarta.persistence.*;

@Entity
public class LineItem {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Product product;

    private int quantity;

    // @ManyToOne
    // @JoinColumn( name = "cart_id" )
    // private Cart cart;

    @ManyToOne
    @JoinColumn( name = "order_id" )
    private Order order;

    public Order getOrder() {
        return order;
    }

    public LineItem() {
    }

    public LineItem( Product product, int quantity ) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    // public Cart getCart() {
    //     return cart;
    // }

    public int getId() {
        return id;
    }

    public void setProduct( Product product ) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    // public void setCart( Cart cart ) {
    //     this.cart = cart;
    // }

    public void setOrder( Order order ) {
        this.order = order;
    }
}