package gov.iti.jets.restapi.controllers;

import gov.iti.jets.domain.models.Product;
import gov.iti.jets.domain.services.CategoryService;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.domain.models.Category;
import gov.iti.jets.persistence.repositories.CategoryRepository;
import gov.iti.jets.restapi.dtos.category.CategoryDto;
import gov.iti.jets.restapi.dtos.category.CategoryProduct;
import gov.iti.jets.restapi.exceptionmappers.MyCustomException;
import gov.iti.jets.restapi.mappers.RestMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Path( "categories" )
@Produces( {"application/json", "application/xml"} )
public class CategoryController {

    @GET
    public Response getAllCategories( @Context UriInfo uriInfo ) throws MyCustomException {


        try {

            Set<Category> categoryList = new HashSet<>( CategoryService.getAllCategories() );

            System.out.println( categoryList );
            GenericEntity<List<CategoryDto>> entity = new GenericEntity<>( RestMapper.categoryMapper( categoryList, uriInfo ) ) {
            };

            return Response.ok().entity( entity ).build();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new MyCustomException( e, "Couldn't retrieve categories" );
        }
    }



    @POST
    public Response addNewCategory( CategoryDto newCategory) throws MyCustomException {

        System.out.println(newCategory);

        try {
            Category category = new Category(newCategory.getName());

            CategoryService.addNewCategory( category );

            return Response.ok().entity(category).build();

        } catch (Exception e) {
            throw new MyCustomException( e, "Category not added");

        }

    }

    @GET
    @Path( "{cid}" )
    public Response getCategoryProducts( @PathParam( "cid" ) int categoryId , @Context UriInfo uriInfo) throws MyCustomException {

        try {

           List<Product> products = CategoryService.getCategoryProducts( categoryId );

            System.out.println( products );
            GenericEntity<List<CategoryProduct>> entity = new GenericEntity<>( RestMapper.categoryProductMapper(products, uriInfo)) {
            };

            return Response.ok().entity( entity ).build();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new MyCustomException( e, "Couldn't retrieve products" );
        }


    }

    @DELETE
    @Path( "{cid}" )
    public Response deleteCategory(@PathParam( "cid" ) int categoryId) throws MyCustomException {
        try{
            Category category = CategoryService.getCategoryById( categoryId );
            List<Product> products = CategoryService.getCategoryProducts( categoryId );

            products.forEach( product ->  ProductService.removeCategoryFromProduct( product, category ) );

            CategoryService.deleteCategory(category);

            return Response.ok().entity( "success" ).build();

        }catch ( Exception e ){
            throw new MyCustomException( e, "couldn't delete category" );
        }
    }

}
