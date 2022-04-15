package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Product;
import jakarta.persistence.EntityManager;

public class ProductRepository extends AbstractRepository<Product>{
    public ProductRepository( EntityManager entityManager ) {
        super( entityManager );
        this.setClazz( Product.class );
    }
}
