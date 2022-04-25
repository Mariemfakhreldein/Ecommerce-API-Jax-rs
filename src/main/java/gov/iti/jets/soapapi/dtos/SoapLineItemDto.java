package gov.iti.jets.soapapi.dtos;

import gov.iti.jets.domain.models.CartLineItem;

public class SoapLineItemDto {
    private int id;
    private int productId;
    private int quantity;

    public SoapLineItemDto() {
    }

    public SoapLineItemDto( CartLineItem cartLineItem ) {
        this.id = cartLineItem.getId();
        this.productId = cartLineItem.getProduct().getId();
        this.quantity = cartLineItem.getQuantity();
    }


    public SoapLineItemDto( int productId, int quantity ) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public SoapLineItemDto( int id, int productId, int quantity ) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId( int productId ) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SoapLineItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
