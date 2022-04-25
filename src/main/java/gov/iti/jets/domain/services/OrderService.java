package gov.iti.jets.domain.services;

import gov.iti.jets.domain.models.Order;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.repositories.OrderRepository;
import gov.iti.jets.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class OrderService {

    public static Order getOrderById( int orderId ) {
        EntityManager em = JpaUtil.createEntityManager();
        OrderRepository or = new OrderRepository( em );

        Optional<Order> optionalOrder = or.findOne( orderId );

        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }else{
            throw new NullPointerException();
        }

    }

    public static void updateOrder( Order retrievedOrder ) {

        EntityManager em = JpaUtil.createEntityManager();
        OrderRepository or = new OrderRepository( em );


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        or.update(retrievedOrder);
        tx.commit();
        em.close();
    }
}
