package gov.iti.jets.domain.services;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.api.dtos.product.ProductRequestDto;
import gov.iti.jets.api.dtos.product.ProductResponseDto;
import gov.iti.jets.api.mappers.ProductMapper;
import gov.iti.jets.domain.models.Category;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.persistence.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.core.UriInfo;

public class ProductService {

    public static List<Product> getAllProducts() {

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);

        List<Product> productEntityList = pr.findAll();


        return productEntityList;
    }

    public static void addNewProduct(Product newProduct) {
        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);

        System.out.println("---------"+newProduct);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        pr.create(newProduct);
        tx.commit();
        em.close();

        System.out.println("product added!!");
    }

    public static Product getProductById( int productId ) {

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);
        Optional<Product>  optionalProduct = pr.findOne(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }else{
            throw new IllegalArgumentException();
        }

    }

    public static void updateProduct(Product updatedProduct){
        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        pr.update( updatedProduct );
        tx.commit();

        em.close();
    }

    public static void deleteProduct(int productId){

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        pr.deleteById( productId );
        tx.commit();

        em.close();
    }


    public static void addCategoryToProduct( Product productEntity, Category categoryEntity ) {

        productEntity.addCategoryToProduct( categoryEntity );
        System.out.println(productEntity);
        updateProduct( productEntity );

    }

    public static void removeCategoryFromProduct( Product productEntity, Category categoryEntity ) {

        productEntity.removeCategoryFromProduct( categoryEntity );

        updateProduct( productEntity );
    }
}