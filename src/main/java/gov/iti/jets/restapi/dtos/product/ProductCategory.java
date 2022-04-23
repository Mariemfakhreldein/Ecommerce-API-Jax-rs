package gov.iti.jets.restapi.dtos.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.restapi.adapters.LinkJsonbAdapter;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class ProductCategory implements Serializable{

    private int id;
    private String name;

    @JsonbTypeAdapter( LinkJsonbAdapter.class )
    List<Link> links = new ArrayList<>();

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

    @Override
    public String toString() {
        return "ProductCategory [id=" + id + ", name=" + name + "]";
    }
}
