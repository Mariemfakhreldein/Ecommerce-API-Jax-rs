package gov.iti.jets;

import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DbPopulator {
    public static void main( String[] args ) {



        //Create some products
        List<Product> products = new ArrayList<>();
        products.add( new Product("IntellijIdeaUltimate", "IDE", new BigDecimal( 5000 )) );
        products.add( new Product("VSCode", "Text editor with extentions", new BigDecimal( 50 )) );
        products.add( new Product("CodeBlocks", "IDE", new BigDecimal( 100 )) );


        //Create Categories
        Category softwareCategory = new Category("Software");
        Category ideCategory = new Category("IDE");
        Category textEditorCategory = new Category("TextEditor");


        //Set category on products
        products.get( 0 ).addCategoryToProduct( softwareCategory );
        products.get( 0 ).addCategoryToProduct( ideCategory );
        products.get( 1 ).addCategoryToProduct( softwareCategory );
        products.get( 1 ).addCategoryToProduct( textEditorCategory );
        products.get( 2 ).addCategoryToProduct( softwareCategory );
        products.get( 2 ).addCategoryToProduct( ideCategory );


        //Persist data
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Persist products
        products.forEach(em::persist);

        //


        tx.commit();


        em.close();
    }
}
