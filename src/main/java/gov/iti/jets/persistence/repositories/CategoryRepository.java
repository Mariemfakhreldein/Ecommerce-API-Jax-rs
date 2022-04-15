package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import jakarta.persistence.EntityManager;

public class CategoryRepository extends AbstractRepository{

    public CategoryRepository( EntityManager entityManager ) {
        super( entityManager );
        this.setClazz( Category.class );
    }


}
