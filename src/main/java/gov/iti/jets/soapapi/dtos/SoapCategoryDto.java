package gov.iti.jets.soapapi.dtos;

public class SoapCategoryDto {
    private int id;
    private String name;

    public SoapCategoryDto(){

    }

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

    public SoapCategoryDto( int id, String name ) {
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
