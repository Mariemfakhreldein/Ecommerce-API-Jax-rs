package gov.iti.jets.domain.dtos.category;

public class CategoryRequestDto {
    
    private String name;

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
