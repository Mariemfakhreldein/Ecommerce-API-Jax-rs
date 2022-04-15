package gov.iti.jets.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory( "rest" );
    }

    public static EntityManager createEntityManager(){
        return emf.createEntityManager();
    }

    public static void main( String[] args ) {

    }
}
