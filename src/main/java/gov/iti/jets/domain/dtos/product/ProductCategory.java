package gov.iti.jets.domain.dtos.product;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductCategory implements Serializable{

    private int id;
    private String name;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategory [id=" + id + ", name=" + name + "]";
    }
}
