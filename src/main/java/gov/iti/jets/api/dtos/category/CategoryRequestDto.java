package gov.iti.jets.api.dtos.category;

public class CategoryRequestDto {

    private int id;
    private String name;

    public CategoryRequestDto( int id, String name ) {
        this.id = id;
        this.name = name;
    }

    public CategoryRequestDto(){

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryRequest [name=" + name + "]";
    }

    
}
