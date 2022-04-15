package gov.iti.jets.domain.dtos.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@JsonbPropertyOrder({"id", "name", "description", "price", "categories"})
@XmlRootElement(name="product")
public class ProductResponseDto implements Serializable{
    
    private int id;
    private String name;
    private String description;
    private BigDecimal price;

    @XmlElementWrapper
    @XmlElement(name = "category")
    private List<ProductCategory> categories;

    List<Link> links = new ArrayList<>();
    
    public ProductResponseDto(int id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = new ArrayList<>();
    }
    
    public ProductResponseDto() {
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
    
    @XmlTransient
    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories){
        this.categories = categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String uri, String rel){
        Link link = Link.fromUri(uri).rel(rel).build();
        links.add(link);
    }


}
