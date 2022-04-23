package gov.iti.jets.persistence.repositories;

import gov.iti.jets.domain.models.Order;
import jakarta.persistence.EntityManager;

public class OrderRepository extends AbstractRepository<Order>{
    public OrderRepository( EntityManager entityManager ) {
        super( entityManager );
        this.setClazz( Order.class );
    }

}
