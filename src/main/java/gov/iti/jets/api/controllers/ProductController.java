package gov.iti.jets.api.controllers;

import gov.iti.jets.domain.dtos.product.ProductRequestDto;
import gov.iti.jets.domain.dtos.product.ProductResponseDto;
import gov.iti.jets.domain.dtos.product.ProductsListResponseDto;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.persistence.JpaUtil;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.repositories.CategoryRepository;
import gov.iti.jets.persistence.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Optional;

@Path( "products" )
@Produces({"application/json","application/xml"})
public class ProductController {

    @GET
    public Response getAllProducts(@Context UriInfo uriInfo)
    {
        ProductsListResponseDto productsList = new ProductsListResponseDto();
        productsList.setProductList(ProductService.getAllProducts(uriInfo));

        String selfUri = uriInfo.getBaseUriBuilder()
        .path(ProductController.class)
        .build()
        .toString();
        productsList.addLink(selfUri,"self");

        GenericEntity<ProductsListResponseDto> genericProducts = new GenericEntity<>(productsList){};

        return Response.ok().entity( genericProducts ).link(selfUri, "self").build();
    }
















    @POST
    public Response addNewProduct( ProductRequestDto productRequestDto ){

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository(em);

        try{

            Product newProduct = new Product(productRequestDto.getName(), productRequestDto.getDescription(), productRequestDto.getPrice());

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist( newProduct );
            tx.commit();
            em.close();

            return Response.ok("New product added!!").entity("new product added!!").build();
        }catch ( Exception e ){
            return Response.notModified("not modified").build();
        }
    }


    @GET
    @Path( "{pid}" )
    public Response getProductById( @PathParam ( "pid" ) int productId){

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository( em );

        Optional<Product> optionalRequestedProduct = pr.findOne( productId );

        System.out.println("ID: "+productId);

        if( optionalRequestedProduct.isPresent()){
            System.out.println(optionalRequestedProduct.get());
            return Response.ok().entity( optionalRequestedProduct.get() ).build();
        }else{
            System.out.println("++++++++++++++not found");
            return Response.noContent().build();
        }

    }


    @PUT
    @Path("{pid}")
    public Response updateProduct(@PathParam( "pid" ) int productId, ProductRequestDto updatedRequestProduct){
        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository( em );
        EntityTransaction tx = em.getTransaction();

        try {

            Optional<Product> optionalProduct = pr.findOne( productId );
            Product updatedProduct = optionalProduct.get();
            System.out.println(updatedProduct);
            updatedProduct.setName( updatedRequestProduct.getName() );
            updatedProduct.setDescription( updatedRequestProduct.getDescription() );
            updatedProduct.setPrice( updatedRequestProduct.getPrice() );

            tx.begin();
            pr.update(updatedProduct);
            tx.commit();

            return Response.ok("Product updated successfully ").entity( updatedProduct ).build();

        } catch ( Exception e){
            return  Response.notModified(e.getMessage()).build();
        }
    }

    @POST
    @Path( "{pid}/categories/{cid}" )
    public Response addCategoryToProduct(@PathParam( "pid" ) int productId, @PathParam( "cid" ) int categoryId){

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository( em );
        CategoryRepository cp = new CategoryRepository( em );
       try {
           Optional<Product> optionalProduct = pr.findOne( productId );
           Optional<Category> optionalCategory = cp.findOne( categoryId );

           if ( optionalProduct.isPresent() && optionalCategory.isPresent() ) {
               Product product = optionalProduct.get();
               Category category = optionalCategory.get();

               product.addCategoryToProduct( category );

               EntityTransaction tx = em.getTransaction();
               tx.begin();
               pr.update( product );
               tx.commit();
               em.close();
               return Response.ok().entity( product ).build();

           } else {
                throw new IllegalArgumentException("Product or Category doesn't exist!");
           }
       }catch ( Exception e ){
           return Response.notModified(e.getMessage()).build();
       }
    }

    @DELETE
    @Path( "{pid}/categories/{cid}" )
    public Response removeCategoryFromProduct(@PathParam( "pid" ) int productId, @PathParam( "cid" ) int categoryId){

        EntityManager em = JpaUtil.createEntityManager();
        ProductRepository pr = new ProductRepository( em );
        CategoryRepository cp = new CategoryRepository( em );
        try {
            Optional<Product> optionalProduct = pr.findOne( productId );
            Optional<Category> optionalCategory = cp.findOne( categoryId );

            if ( optionalProduct.isPresent() && optionalCategory.isPresent() ) {
                Product product = optionalProduct.get();
                Category category = optionalCategory.get();

                product.removeCategoryFromProduct( category );

                EntityTransaction tx = em.getTransaction();
                tx.begin();
                pr.update( product );
                tx.commit();
                em.close();
                return Response.ok().entity( product ).build();

            } else {
                throw new IllegalArgumentException("Category or Product doesn't exist!");
            }
        }catch ( Exception e ){
            return Response.notModified(e.getMessage()).build();
        }
    }

}