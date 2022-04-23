package gov.iti.jets.api.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gov.iti.jets.api.controllers.CategoryController;
import gov.iti.jets.api.controllers.ProductController;
import gov.iti.jets.api.dtos.product.ProductCategory;
import gov.iti.jets.api.dtos.product.ProductResponseDto;
import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import jakarta.ws.rs.core.UriInfo;

public class ProductMapper {
    
    public static ProductResponseDto mapProductToProductResponse(Product inputProduct, UriInfo uriInfo){

        ProductResponseDto mappedProduct = new ProductResponseDto(inputProduct.getId(), inputProduct.getName(), inputProduct.getDescription(), inputProduct.getPrice());
        
        mappedProduct.setCategories(productCategoryMapper(inputProduct.getCategories(), uriInfo));


        String selfUri = uriInfo.getAbsolutePathBuilder().path(ProductController.class).path(Long.toString(mappedProduct.getId())).build().toString();

        mappedProduct.addLink(selfUri, "self");

        return mappedProduct;
    }

    private static List<ProductCategory> productCategoryMapper(Set<Category> inputCategories, UriInfo uriInfo){

        List<ProductCategory> mappedCategories = new ArrayList<>();
        inputCategories.forEach(category ->{
            String selfUri = uriInfo.getBaseUriBuilder().path( CategoryController.class).path(Long.toString( category.getId())).build().toString();
            ProductCategory mappedProductCategory = new ProductCategory(category.getId(), category.getName());
            mappedProductCategory.addLink( selfUri, "self" );
            mappedCategories.add(mappedProductCategory);
        });
        return mappedCategories;
    }
}
