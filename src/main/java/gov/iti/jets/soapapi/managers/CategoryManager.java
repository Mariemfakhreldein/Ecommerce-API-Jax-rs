package gov.iti.jets.soapapi.managers;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.domain.services.CategoryService;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.restapi.dtos.category.CategoryProduct;
import gov.iti.jets.restapi.exceptionmappers.MyCustomException;
import gov.iti.jets.restapi.mappers.RestMapper;
import gov.iti.jets.soapapi.custom.SoapCustomException;
import gov.iti.jets.soapapi.dtos.SoapCategoryDto;
import gov.iti.jets.soapapi.dtos.SoapCategoryProductDto;
import gov.iti.jets.soapapi.dtos.SoapResponse;
import gov.iti.jets.soapapi.mappers.ResponseMapper;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@WebService
public class CategoryManager {

    public List<SoapCategoryDto> getAllCategories() {

        try {

            List<Category> categories = CategoryService.getAllCategories();

            return categories.stream().map( ResponseMapper::mapCategoryToSoapCategoryDto ).collect( Collectors.toList() );
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new SoapCustomException( "Couldn't retrieve category list", e );
        }

    }

    public SoapResponse addNewCategory(SoapCategoryDto newCategory){
        try{
            Category category = new Category( newCategory.getName() );
            CategoryService.addNewCategory( category );

            return new SoapResponse("Category added successfully");
        }catch ( Exception e ){
            e.printStackTrace();
            throw new SoapCustomException( "Couldn't retrieve category list", e );
        }

    }

    public List<SoapCategoryProductDto> getCategoryProducts(int categoryId){
        try {

            List<Product> products = CategoryService.getCategoryProducts( categoryId );

            return products.stream().map( product -> new SoapCategoryProductDto( product.getId(),  product.getDescription(), product.getName()) ).collect( Collectors.toList());

        } catch ( Exception e ) {
            e.printStackTrace();
            throw new SoapCustomException( "Couldn't retrieve category list", e );
        }

    }

    public SoapResponse deleteCategory(int categoryId){
        try{
            Category category = CategoryService.getCategoryById( categoryId );
            List<Product> products = CategoryService.getCategoryProducts( categoryId );

            products.forEach( product ->  ProductService.removeCategoryFromProduct( product, category ) );

            CategoryService.deleteCategory(category);

            return new SoapResponse("Category deleted successfully");

        }catch ( Exception e ){
            e.printStackTrace();
            throw new SoapCustomException( "Couldn't retrieve category list", e );
        }
    }

}
