package gov.iti.jets.persistence.entities;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessorOrder;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonbPropertyOrder( {"id", "name", "description", "price", "categories"} )
@XmlRootElement
// @XmlAccessorOrder("id", "name", "description", "price", "categories")
// @XmlAccessorType()
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @XmlElementWrapper
    @XmlElement(name = "category")
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public Product( String name, String description, BigDecimal price ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = new HashSet<>();
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void addCategoryToProduct(Category category){
        this.categories.add( category );
        category.getProducts().add(this);
    }
    public void removeCategoryFromProduct(Category category){
        this.categories.remove( category );
        category.getProducts().remove( this );
    }
}
