package gov.iti.jets.restapi.dtos.user;

import gov.iti.jets.domain.enums.Status;
import gov.iti.jets.restapi.adapters.LinkJsonbAdapter;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OrderDto {

    private int id;
    private Status status;
    private List<LineItemDto> lineItemDtoList;


    @JsonbTypeAdapter( LinkJsonbAdapter.class)
    List<Link> links = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public List<LineItemDto> getLineItemDtoList() {
        return lineItemDtoList;
    }

    public void setLineItemDtoList( List<LineItemDto> lineItemDtoList ) {
        this.lineItemDtoList = lineItemDtoList;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
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
