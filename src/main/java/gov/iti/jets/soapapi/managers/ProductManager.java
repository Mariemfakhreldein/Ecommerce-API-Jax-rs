package gov.iti.jets.soapapi.managers;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.domain.services.CategoryService;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.restapi.exceptionmappers.MyCustomException;
import gov.iti.jets.soapapi.custom.SoapCustomException;
import gov.iti.jets.soapapi.dtos.SoapProductDto;
import gov.iti.jets.soapapi.mappers.ResponseMapper;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@WebService
public class ProductManager {
    public List<SoapProductDto> getAllProducts() {

        try {
            List<Product> productList = ProductService.getAllProducts();

            return productList.stream()
                    .map( ResponseMapper::mapProductToProductResponse )
                    .collect( Collectors.toList() );

        }catch ( Exception e ){
            e.printStackTrace();
            throw new SoapCustomException("Couldn't load products", e);
        }
    }


    public SoapProductDto updateProduct( int productId , SoapProductDto updatedRequestProduct) {
        try {
            Product updatedProduct = ProductService.getProductById( productId );

            updatedProduct.setName(updatedRequestProduct.getName());
            updatedProduct.setDescription(updatedRequestProduct.getDescription());
            updatedProduct.setPrice(updatedRequestProduct.getPrice());

            ProductService.updateProduct( updatedProduct );

            return ResponseMapper.mapProductToProductResponse( updatedProduct );

        } catch (Exception e) {
            e.printStackTrace();
            throw new SoapCustomException("Product not updated", e );
        }
    }

    public SoapProductDto getProductById(int productId){

        try{
            Product retrievedProduct = ProductService.getProductById(productId);

            return ResponseMapper.mapProductToProductResponse( retrievedProduct );
        } catch ( Exception e ){
            e.printStackTrace();
            throw new SoapCustomException("No product with this id", e );
        }

    }

    public SoapProductDto deleteProductById(int productId) {

        try{

            Product productToDelete = ProductService.getProductById( productId );

            ProductService.deleteProduct(productId);

            return ResponseMapper.mapProductToProductResponse( productToDelete );

        }catch(Exception ex){
            ex.printStackTrace();
            throw new SoapCustomException( "Couldn't delete product", ex );
        }
    }

    public SoapProductDto addNewProduct( SoapProductDto productToAdd) {
        try{
            Product newProduct = new Product(productToAdd.getName(), productToAdd.getDescription(), productToAdd.getPrice());

            ProductService.addNewProduct( newProduct );

            return  ResponseMapper.mapProductToProductResponse( newProduct);
        }catch ( Exception e ){
            throw new SoapCustomException( "Couldn't add product", e );
        }
    }

    public SoapProductDto addCategoryToProduct(int productId, int categoryId){
        try {

            Product productEntity = ProductService.getProductById( productId );
            Category categoryEntity = CategoryService.getCategoryById( categoryId );

            ProductService.addCategoryToProduct(productEntity, categoryEntity);

            return ResponseMapper.mapProductToProductResponse( productEntity );

        } catch (Exception e) {
            throw new SoapCustomException( "Category not added", e );
        }
    }


    public SoapProductDto removeCategoryFromProduct(int productId, int categoryId){
        try {

            Product productEntity = ProductService.getProductById( productId );
            Category categoryEntity = CategoryService.getCategoryById( categoryId );

            ProductService.removeCategoryFromProduct(productEntity, categoryEntity);

            return ResponseMapper.mapProductToProductResponse( productEntity );

        } catch (Exception e) {
            throw new SoapCustomException( "Category not removed", e );
        }
    }

//    public static void main( String[] args ) {
//        ProductManager pm = new ProductManager();
//
//        System.out.println("\n\ngetAllProductsById" + pm.getAllProducts());
//        System.out.println("\n\ngetProductById"+pm.getProductById(202));
//        System.out.println("\n\ndeleteProduct"+ pm.deleteProductById( 252 ));
//        System.out.println("\n\n add new "+ pm.addNewProduct( new SoapProductDto("soap product4","testing",new BigDecimal(  500)) ));
//        System.out.println("\n\n update" + pm.updateProduct( 152, new SoapProductDto("soap product2", "updated description", new BigDecimal( 22222 ))));
//        System.out.println("add category"+ pm.addCategoryToProduct( 202,1 ));
//        System.out.println("remove category"+ pm.removeCategoryFromProduct( 202,1 ));
//
//    }

}
