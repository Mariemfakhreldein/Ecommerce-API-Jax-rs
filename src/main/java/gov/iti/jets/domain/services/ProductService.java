package gov.iti.jets.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gov.iti.jets.domain.dtos.product.ProductRequestDto;
import gov.iti.jets.domain.dtos.product.ProductResponseDto;
import gov.iti.jets.domain.mappers.ProductMapper;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.core.UriInfo;

public class ProductService {

    public static List<ProductResponseDto> getAllProducts(UriInfo uriInfo) {

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);

        List<Product> productList = pr.findAll();

        List<ProductResponseDto> productResponseList = new ArrayList<>();

        productList.forEach((product) -> productResponseList.add(ProductMapper.mapProductToProductResponse(product, uriInfo)));

        return productResponseList;
    }

    public static void addNewProduct(ProductRequestDto productRequestDto) {
        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);

        Product newProduct = new Product(productRequestDto.getName(), productRequestDto.getDescription(), productRequestDto.getPrice());

        System.out.println("---------"+newProduct);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        pr.create(newProduct);
        tx.commit();
        em.close();

        System.out.println("product added!!");
    }

    public static ProductResponseDto getProductById( int productId , UriInfo uriInfo) {

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);
        Optional<Product>  optionalProduct = pr.findOne(productId);
        if(optionalProduct.isPresent()){
            return ProductMapper.mapProductToProductResponse( optionalProduct.get(), uriInfo );
        }
        else{
            throw new IllegalArgumentException();
        }

    }
}