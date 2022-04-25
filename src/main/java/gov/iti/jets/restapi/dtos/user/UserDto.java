package gov.iti.jets.restapi.dtos.user;

import gov.iti.jets.domain.enums.Role;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.restapi.adapters.LinkJsonbAdapter;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    @JsonbTypeAdapter( LinkJsonbAdapter.class)
    List<Link> links = new ArrayList<>();


    public UserDto( User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName =  user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public UserDto(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
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
        return "UserDto{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
