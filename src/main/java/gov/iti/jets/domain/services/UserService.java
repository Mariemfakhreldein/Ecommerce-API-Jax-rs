package gov.iti.jets.domain.services;

import gov.iti.jets.domain.models.CartLineItem;
import gov.iti.jets.domain.models.Order;
import gov.iti.jets.domain.models.User;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class UserService {

    public static List<User> getAllUsers(){
        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository( em );

        return ur.findAll();
    }


    public static User getUserById(int userId){
        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository( em );

        Optional<User> optionalUser = ur.findOne( userId );

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new NullPointerException();
        }

    }

    public static void addNewUser(User newUser){

        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        ur.create(newUser);
        tx.commit();
        em.close();
    }

    public static void updateUser(User userToUpdate){

        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        ur.update(userToUpdate);
        tx.commit();
        em.close();

    }

    public static void deleteUser(User userToDelete){

        EntityManager em = JpaUtil.createEntityManager();
        UserRepository ur = new UserRepository(em);


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        ur.deleteById(userToDelete.getId());
        tx.commit();
        em.close();
    }


    public static void addItemToCart( int userId, CartLineItem lineItem){
        User user = UserService.getUserById( userId );
        user.getCart().addItemToCart( lineItem );
        UserService.updateUser( user );
    }

    public static void removeItemFromCart( int userId, int itemId ) {

        User user = UserService.getUserById( userId );
        user.getCart().removeItemFromCart( itemId );

        UserService.updateUser( user );
    }

    public static void emptyCart( int userId ) {
        User user = UserService.getUserById( userId );
        user.getCart().getLineItems().clear();
        UserService.updateUser( user );
    }

    public static void placeOrder( User user ) {

        Order order = new Order(user.getCart());

        System.out.println("order "+ order);

        user.addOrderToUser( order );
        user.getCart().getLineItems().clear();

        UserService.updateUser( user );

        System.out.println("updated useer" + user);

    }
}
