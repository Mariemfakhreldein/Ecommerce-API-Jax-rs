package gov.iti.jets.restapi.controllers;

import java.util.Optional;

import gov.iti.jets.domain.enums.Status;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.domain.services.OrderService;
import gov.iti.jets.domain.services.UserService;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.Order;
import gov.iti.jets.persistence.repositories.OrderRepository;
import gov.iti.jets.restapi.dtos.order.OrderStatusRequest;
import gov.iti.jets.restapi.dtos.user.OrderDto;
import gov.iti.jets.restapi.dtos.user.UserDto;
import gov.iti.jets.restapi.exceptionmappers.MyCustomException;
import gov.iti.jets.restapi.mappers.RestMapper;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("orders")
public class OrderController {
    
    @GET
    @Path("{oid}")
    public Response getOrderById( @PathParam("oid") int orderId, @Context UriInfo uriInfo ) throws MyCustomException {

        try{
            Order retrievedOrder = OrderService.getOrderById(orderId);

            OrderDto responseOrder = RestMapper.mapOrderToOrderDto( retrievedOrder, uriInfo );

            return Response.ok().entity( responseOrder ).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No order with this id" );
        }catch ( Exception e ){
            throw new MyCustomException( e, "Couldn't retrieve order" );
        }

    }

    @PUT
    @Path("{oid}")
    public Response changeOrderStatus( @PathParam("oid") int orderId, OrderStatusRequest statusHolder ) throws MyCustomException {

        try{
            System.out.println("*******************"+statusHolder.getStatus());
            Order retrievedOrder = OrderService.getOrderById(orderId);

            retrievedOrder.setStatus( statusHolder.getStatus() );

            OrderService.updateOrder(retrievedOrder);

            return Response.ok().entity( "Status updated" ).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No order with this id" );
        }catch ( Exception e ){
            throw new MyCustomException( e, "Couldn't update status" );
        }

    }

}
