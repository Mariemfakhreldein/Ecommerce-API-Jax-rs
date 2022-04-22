package gov.iti.jets.domain.dtos.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.api.adapters.LinkJsonbAdapter;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name="products")
@JsonbPropertyOrder({"productList", "links"})
public class ProductsListResponseDto implements Serializable{

    private List<ProductResponseDto> productList;

    @JsonbTypeAdapter(LinkJsonbAdapter.class)
    private List<Link> links = new ArrayList<>();

    public ProductsListResponseDto(List<ProductResponseDto> productList) {
        this.productList = productList;
    }

    public ProductsListResponseDto() {
    }

    @XmlElement(name="product")
    public List<ProductResponseDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductResponseDto> productList) {
        this.productList = productList;
    }

    @XmlElement
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
        return "ProductsListResponseDto [links=" + links + ", productList=" + productList + "]";
    }
    
}
