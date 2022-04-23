package gov.iti.jets.restapi.controllers;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;

@Path("users")
public class UserController {

    @GET
    public Response getAllUsers(){
        
        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);

        List<User> users = ur.findAll();
        GenericEntity<List<User>> genericUsers = new GenericEntity<>(users){};

        return Response.ok().entity(users).build();
    }

    @POST
    public Response addNewUser(User user){

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());

        System.out.println(newUser);

        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);

        try{

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            ur.create(newUser);
            tx.commit();
            // em.flush();

            return Response.ok().entity(newUser).build();

        }catch(Exception e){
            return Response.notModified().build();
        }
    }



    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int userId,User user){

        
        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);
        
        
        try{

            Optional<User> optionalUser = ur.findOne(userId);
            User updatedUser = optionalUser.get();
            
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());


            System.out.println(updatedUser);

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            ur.update(updatedUser);
            tx.commit();
            // em.flush();

            return Response.ok().entity(updatedUser).build();

        }catch(Exception e){
            return Response.notModified().build();
        }
    }



    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int userId){

        
        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);
        
        
        try{

            Optional<User> optionalUser = ur.findOne(userId);
            User deletedUser = optionalUser.get();

            System.out.println(deletedUser);

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            ur.delete(deletedUser);
            tx.commit();
            // em.flush();

            return Response.ok().entity(deletedUser).build();

        }catch(Exception e){
            return Response.notModified().build();
        }
    }



    @GET
    @Path("{uid}/orders")
    public Response getUserOrders(@PathParam("uid") int userId){
        
        return null;
    }





}
