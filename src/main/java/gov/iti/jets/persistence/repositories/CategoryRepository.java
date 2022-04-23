package gov.iti.jets.persistence.repositories;

import gov.iti.jets.domain.models.Category;
import jakarta.persistence.EntityManager;

public class CategoryRepository extends AbstractRepository{

    public CategoryRepository( EntityManager entityManager ) {
        super( entityManager );
        this.setClazz( Category.class );
    }


}
