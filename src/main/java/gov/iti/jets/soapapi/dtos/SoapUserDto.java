package gov.iti.jets.soapapi.dtos;

import gov.iti.jets.domain.enums.Role;
import gov.iti.jets.domain.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SoapUserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public SoapUserDto( ) {
    }


    public SoapUserDto( User user ) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SoapUserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
