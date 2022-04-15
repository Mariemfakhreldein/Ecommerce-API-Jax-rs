package gov.iti.jets.domain.dtos.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequestDto implements Serializable {
    private String name;
    private String description;
    private BigDecimal price;

    public ProductRequestDto() {
    }

    public ProductRequestDto( String name, String description, BigDecimal price ) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "price = " + price + ")";
    }
}
