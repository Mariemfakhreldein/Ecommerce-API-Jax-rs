package gov.iti.jets.soapapi.dtos;

public class SoapProductCategory {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public SoapProductCategory( int id, String name ) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SoapProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
