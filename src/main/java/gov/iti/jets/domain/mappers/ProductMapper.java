package gov.iti.jets.domain.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gov.iti.jets.api.controllers.ProductController;
import gov.iti.jets.domain.dtos.product.ProductCategory;
import gov.iti.jets.domain.dtos.product.ProductResponseDto;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import jakarta.ws.rs.core.UriInfo;

public class ProductMapper {
    
    public static ProductResponseDto mapProductToProductResponse(Product inputProduct, UriInfo uriInfo){

        ProductResponseDto mappedProduct = new ProductResponseDto(inputProduct.getId(), inputProduct.getName(), inputProduct.getDescription(), inputProduct.getPrice());
        
        mappedProduct.setCategories(productCategoryMapper(inputProduct.getCategories()));


        String selfUri = uriInfo.getAbsolutePathBuilder().path(ProductController.class).path(Long.toString(mappedProduct.getId())).build().toString();

        mappedProduct.addLink(selfUri, "self");

        return mappedProduct;
    }

    private static List<ProductCategory> productCategoryMapper(Set<Category> inputCategories){

        List<ProductCategory> mappedCategories = new ArrayList<>();
        inputCategories.forEach(category ->{
            mappedCategories.add(new ProductCategory(category.getId(), category.getName()));
        });
        return mappedCategories;
    }
}
