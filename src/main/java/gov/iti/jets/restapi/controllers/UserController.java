package gov.iti.jets.restapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import gov.iti.jets.domain.models.CartLineItem;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.domain.services.UserService;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.restapi.dtos.user.LineItemDto;
import gov.iti.jets.restapi.dtos.user.OrderDto;
import gov.iti.jets.restapi.dtos.user.UserDto;
import gov.iti.jets.restapi.exceptionmappers.MyCustomException;
import gov.iti.jets.restapi.mappers.RestMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("users")
@Produces({"application/json", "application/xml"})
public class UserController {

    @GET
    public Response getAllUsers( @Context UriInfo uriInfo ) throws MyCustomException {
        try {

            GenericEntity<List<UserDto>> genericUsers = new GenericEntity<>( UserService.getAllUsers().stream().map( user ->  RestMapper.mapUserToUserDto( user, uriInfo ) ).collect( Collectors.toList()) ) {
            };

            return Response.ok().entity( genericUsers ).build();

        }catch ( Exception e ){
            throw new MyCustomException( e, "Couldn't retrieve users list" );
        }
    }




    @GET
    @Path("{uid}")
    public Response getUserById(@PathParam( "uid" ) int userId, @Context UriInfo uriInfo) throws MyCustomException {

        try{
            User retrievedUser = UserService.getUserById(userId);

            UserDto responseUser = RestMapper.mapUserToUserDto( retrievedUser , uriInfo);

            return Response.ok().entity( responseUser ).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch ( Exception e ){
            throw new MyCustomException( e, "Couldn't retrieve user" );
        }
    }


    @POST
    public Response addNewUser(UserDto addRequestUser, @Context UriInfo uriInfo) throws MyCustomException {

        try{
            User newUser = new User(addRequestUser.getFirstName(), addRequestUser.getLastName(), addRequestUser.getEmail(), addRequestUser.getRole());

            UserService.addNewUser( newUser );

            return Response.ok().entity(RestMapper.mapUserToUserDto( newUser, uriInfo )).build();

        }catch(Exception e){
            throw new MyCustomException( e, "User not added" ) ;
        }
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int userId,UserDto updatedRequestUser, @Context UriInfo uriInfo) throws MyCustomException {

        try{

            User existingUser = UserService.getUserById( userId );

            existingUser.setFirstName( updatedRequestUser.getFirstName() );
            existingUser.setLastName( updatedRequestUser.getLastName() );
            existingUser.setEmail( updatedRequestUser.getEmail() );
            existingUser.setRole(updatedRequestUser.getRole());

            UserService.updateUser( existingUser );

            return Response.ok().entity(RestMapper.mapUserToUserDto( existingUser, uriInfo )).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch(Exception e){
            throw new MyCustomException( e, "User not updated" );
        }
    }



    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int userId) throws MyCustomException {

        try{

            User user = UserService.getUserById( userId );

            UserService.deleteUser( user );

            return Response.ok().entity(new UserDto(user)).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch(Exception e){
            throw new MyCustomException( e, "User not updated" );
        }

    }






    @POST
    @Path( "{uid}/cart" )
    public Response addItemToCart( @PathParam( "uid" ) int userId, LineItemDto addedRequestItem) throws MyCustomException {

        try{

            Product lineItemProduct = ProductService.getProductById( addedRequestItem.getProductId() );

            CartLineItem addedItem = new CartLineItem();
            addedItem.setProduct( lineItemProduct );
            System.out.println(addedItem.getProduct().getId());
            addedItem.setQuantity( addedRequestItem.getQuantity() );

            UserService.addItemToCart(userId, addedItem);

            return Response.ok().entity( "Item added successfully" ).build();

        }catch ( Exception e ){
            throw new MyCustomException( e, "Item not added" );
        }

    }



    @GET
    @Path("{uid}/cart")
    public Response getUserCart(@PathParam( "uid" ) int userId) throws MyCustomException {
        try{
            User user = UserService.getUserById( userId );

            List<LineItemDto> lineItemDtoList = RestMapper.mapLineItemsToLineItemDtos(user.getCart().getLineItems());
            GenericEntity<List<LineItemDto>> genericCart = new GenericEntity<>( lineItemDtoList ) {
            };

            return Response.ok().entity( genericCart ).build();

        }catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch(Exception e){
            throw new MyCustomException( e, "User not updated" );
        }
    }



    @DELETE
    @Path( "{uid}/cart/{itemId}" )
    public Response removeItemFromCart( @PathParam( "uid" ) int userId, @PathParam( "itemId" ) int itemId) throws MyCustomException {

        try{

            UserService.removeItemFromCart(userId, itemId);

            return Response.ok().entity( "Item removed successfully" ).build();

        }catch ( Exception e ){
            throw new MyCustomException( e, "Item not removed" );
        }

    }



    @DELETE
    @Path( "{uid}/cart" )
    public Response emptyUserCart( @PathParam( "uid" ) int userId) throws MyCustomException {

        try{

            UserService.emptyCart(userId);

            return Response.ok().entity( "Cart emptied successfully" ).build();

        }catch ( Exception e ){
            throw new MyCustomException( e, "Item not removed" );
        }

    }


    @POST
    @Path( "{uid}/orders" )
    public Response placeOrder(@PathParam( "uid" ) int userId) throws MyCustomException {
        try{

            User user = UserService.getUserById( userId );
            UserService.placeOrder(user);
            return Response.ok().entity( "Order placed successfully" ).build();

        } catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch ( Exception e ){
        throw new MyCustomException( e, "Order not placed" );
    }
    }


    @GET
    @Path( "{uid}/orders" )
    public Response getUserOrders(@PathParam( "uid" ) int userId, @Context UriInfo uriInfo) throws MyCustomException {
        try{

            User user = UserService.getUserById( userId );
            List<OrderDto> orderDtoList = user.getOrders().stream().map( order ->  RestMapper.mapOrderToOrderDto(order, uriInfo) ).collect( Collectors.toList());

            System.out.println(user.getOrders());

            GenericEntity<List<OrderDto>> genericOrders = new GenericEntity<>( orderDtoList ) {
            };

            return Response.ok().entity( genericOrders ).build();

        } catch(NullPointerException ne){
            throw new MyCustomException( ne, "No user with this id" );
        }catch ( Exception e ){
            throw new MyCustomException( e, "Couldn't retrieve orders" );
        }
    }

}
