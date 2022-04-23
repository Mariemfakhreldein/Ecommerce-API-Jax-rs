package gov.iti.jets.api.controllers;

import gov.iti.jets.api.exceptionmappers.MyCustomException;
import gov.iti.jets.api.dtos.ResponseMessage;
import gov.iti.jets.api.dtos.product.ProductRequestDto;
import gov.iti.jets.api.dtos.product.ProductResponseDto;
import gov.iti.jets.api.dtos.product.ProductsListResponseDto;
import gov.iti.jets.api.mappers.ProductMapper;
import gov.iti.jets.domain.services.CategoryService;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.List;

@Path("products")
@Produces({ "application/json", "application/xml" })
@Consumes("application/json")
public class ProductController {

    @GET
    public Response getAllProducts(@Context UriInfo uriInfo) throws MyCustomException {

            ProductsListResponseDto productsList = new ProductsListResponseDto();

        try{
            List<ProductResponseDto> productResponseList = new ArrayList<>();

            ProductService.getAllProducts().forEach((product) -> productResponseList.add( ProductMapper.mapProductToProductResponse(product, uriInfo)));

            productsList.setProductList( productResponseList );

        }catch(Exception e){
            throw new MyCustomException(e, "There was a problem getting the products");
        }

        productsList.addLink(getSelfUri(uriInfo), "self");

        GenericEntity<ProductsListResponseDto> genericProducts = new GenericEntity<>(productsList) {
        };

        return Response.ok().entity(genericProducts).link(getSelfUri( uriInfo ), "self").build();
    }
    

//     @GET
    public Response getException() throws MyCustomException {
        throw new MyCustomException(new NullPointerException("testiiiiiiiing"),"dsf");
    }

    @POST
    public Response addNewProduct(ProductRequestDto productRequestDto) throws MyCustomException {
        try {
            System.out.println(productRequestDto);

            Product newProduct = new Product(productRequestDto.getName(), productRequestDto.getDescription(), productRequestDto.getPrice());

            ProductService.addNewProduct(newProduct);

            return Response.ok(new ResponseMessage(Status.FOUND.getStatusCode(),"New product added!!")).build();

        } catch (Exception e) {
            throw new MyCustomException(e,"Could'nt add product!!");
        }
    }

    @GET
    @Path("{pid}")
    public Response getProductById(@PathParam("pid") int productId, @Context UriInfo uriInfo) throws MyCustomException {

        System.out.println("ID: " + productId);

        try{
            Product retrievedProduct = ProductService.getProductById(productId);

            ProductResponseDto productResponse = ProductMapper.mapProductToProductResponse( retrievedProduct, uriInfo );

            return Response.ok().entity(productResponse).build();
        } catch ( Exception e ){
           throw new MyCustomException( e, "No product with this id" );
        }

    }

    @PUT
    @Path("{pid}")
    public Response updateProduct(@PathParam("pid") int productId, ProductRequestDto updatedRequestProduct) throws MyCustomException {

        try {
            Product updatedProduct = ProductService.getProductById( productId );

            updatedProduct.setName(updatedRequestProduct.getName());
            updatedProduct.setDescription(updatedRequestProduct.getDescription());
            updatedProduct.setPrice(updatedRequestProduct.getPrice());

            ProductService.updateProduct( updatedProduct );

            return Response.ok("Product updated successfully ").entity(updatedProduct).build();

        } catch (Exception e) {
            throw new MyCustomException( e , "Couldn't update product" );
        }
    }

    @DELETE
    @Path( "{pid}" )
    public Response deleteProduct(@PathParam( "pid" ) int productId, @Context UriInfo uriInfo ) throws MyCustomException {

        try{

            Product productToDelete = ProductService.getProductById( productId );

            ProductService.deleteProduct(productId);

            return Response.ok().entity(productToDelete).build();

        }catch(Exception ex){
            throw new MyCustomException( ex, "Couldn't delete product" );
        }
    }

    @POST
    @Path("{pid}/categories/{cid}")
    public Response addCategoryToProduct(@PathParam("pid") int productId, @PathParam("cid") int categoryId, @Context UriInfo uriInfo) throws MyCustomException {

        try {

           Product productEntity = ProductService.getProductById( productId );
           Category categoryEntity = CategoryService.getCategoryById( categoryId );


           ProductService.addCategoryToProduct(productEntity, categoryEntity);

           ProductResponseDto productResponse = ProductMapper.mapProductToProductResponse(productEntity, uriInfo);

           return Response.ok().entity(productResponse).build();

        } catch (Exception e) {
            throw new MyCustomException(e, "Couldn't add category to product");
        }
    }

    @DELETE
    @Path("{pid}/categories/{cid}")
    public Response removeCategoryFromProduct(@PathParam("pid") int productId, @PathParam("cid") int categoryId, @Context UriInfo uriInfo) throws MyCustomException {

        try {
            Product productEntity = ProductService.getProductById( productId );
            Category categoryEntity = CategoryService.getCategoryById( categoryId );


            ProductService.removeCategoryFromProduct(productEntity, categoryEntity);

            ProductResponseDto productResponse = ProductMapper.mapProductToProductResponse(productEntity, uriInfo);

            return Response.ok().entity(productResponse).build();

        } catch (Exception e) {
            throw new MyCustomException(e, "Couldn't remove category from product");
        }
    }

    private String getSelfUri(UriInfo uriInfo){
        return   uriInfo.getBaseUriBuilder()
                .path(ProductController.class)
                .build()
                .toString();
    }

}