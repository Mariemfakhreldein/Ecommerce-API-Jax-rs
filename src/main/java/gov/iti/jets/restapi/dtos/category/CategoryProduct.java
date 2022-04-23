package gov.iti.jets.restapi.dtos.category;

import gov.iti.jets.restapi.adapters.LinkJsonbAdapter;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryProduct {
    private int id;
    private String description;
    private String name;


    @JsonbTypeAdapter( LinkJsonbAdapter.class)
    List<Link> links = new ArrayList<>();

    public CategoryProduct( int id, String description, String name ) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
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
        return "CategoryProduct{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
