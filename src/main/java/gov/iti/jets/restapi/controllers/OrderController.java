package gov.iti.jets.restapi.controllers;

import java.util.Optional;

import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.Order;
import gov.iti.jets.persistence.repositories.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("orders")
public class OrderController {
    
    @GET
    @Path("{oid}")
    public Response getUserOrders(@PathParam("oid") int orderId){
        
        EntityManager em = JpaUtil.createEntityManager();
        OrderRepository or = new OrderRepository(em);

        Optional<Order> optionalOrder = or.findOne(orderId);
        
        if(optionalOrder.isPresent()){

            return Response.ok().entity(optionalOrder.get()).build();
        }else{
            return Response.noContent().build();
        }
    }

}
