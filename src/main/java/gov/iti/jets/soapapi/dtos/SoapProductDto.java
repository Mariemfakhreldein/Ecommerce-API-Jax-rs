package gov.iti.jets.soapapi.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SoapProductDto {

    private int id;
    private String name;
    private String description;
    private BigDecimal price;

    private List<SoapProductCategory> categories;

    public SoapProductDto( String name, String description, BigDecimal price ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = new ArrayList<>();
    }

    public SoapProductDto( int id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = new ArrayList<>();
    }

    public SoapProductDto() {
        this.categories = new ArrayList<>();
    }

    public int getId() {
        return id;
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

    public List<SoapProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SoapProductCategory> categories){
        this.categories = categories;
    }

    public void setId(int id) {
        this.id = id;
    }

}
