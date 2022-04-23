package gov.iti.jets.domain.services;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class CategoryService {


    public static List<Category> getAllCategories(){

        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository(em);

        return cr.findAll();
    }



    public static void addNewCategory(Category category){

        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository(em);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(category);
        tx.commit();
        em.close();


    }



    public static List<Product> getCategoryProducts( int categoryId){
        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository( em );

        Optional<List<Product>> optionalProducts =  cr.getProductListOfCategory(em, categoryId);
        if( optionalProducts.isPresent() ){
            return optionalProducts.get();
        }else{
            throw new RuntimeException();
        }
    }






    public static Category getCategoryById( int categoryId){

        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository( em );

        Optional<Category> optionalRetrievedCategory = cr.findOne( categoryId );
        if( optionalRetrievedCategory.isPresent() ){
            return optionalRetrievedCategory.get();
        }else{
            throw new IllegalArgumentException();
        }

    }

    public static void deleteCategory( Category category ) {
        EntityManager em = JpaUtil.createEntityManager();
        CategoryRepository cr = new CategoryRepository( em );

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        cr.deleteById( category.getId() );
        tx.commit();
        em.close();
    }
}
