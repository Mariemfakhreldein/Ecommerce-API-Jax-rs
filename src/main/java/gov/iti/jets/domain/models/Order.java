package gov.iti.jets.domain.models;

import jakarta.persistence.*;

import java.util.List;

import gov.iti.jets.domain.enums.Status;

@Entity
@Table( name = "orders" )
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany( mappedBy = "order", fetch = FetchType.EAGER )
    private List<LineItem> lineItems;

    @ManyToOne
    private User maker;

    @Enumerated( EnumType.STRING )
    private Status status;

    public Order() {
    }

    // public Order( Cart cart ) {
    //     this.lineItems = List.copyOf( cart.getLineItems() );
    //     this.maker = cart.getOwner();
    //     this.status = Status.PENDING;
    // }

    public User getMaker() {
        return maker;
    }

    public int getId() {
        return id;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems( List<LineItem> lineItems ) {
        this.lineItems = lineItems;
    }

    public void setMaker( User maker ) {
        this.maker = maker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }

    public void addLineItemToOrder(LineItem lineItem){
        this.lineItems.add( lineItem );
        lineItem.setOrder( this );
    }
}
