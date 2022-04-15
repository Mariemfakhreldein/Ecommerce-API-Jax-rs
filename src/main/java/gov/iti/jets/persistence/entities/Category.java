package gov.iti.jets.persistence.entities;


import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class Category {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonbTransient
    @XmlTransient
    private Set<Product> products;

    public Category(String name){
        this.name = name;
        this.products = new HashSet<>();
    }

    public Category() {
        this.products = new HashSet<>();
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

    public Set<Product> getProducts() {
        return products;
    }

}
