package gov.iti.jets.domain.services;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.domain.dtos.product.ProductResponseDto;
import gov.iti.jets.domain.mappers.ProductMapper;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
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

}