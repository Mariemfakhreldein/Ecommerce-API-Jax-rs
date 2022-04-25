package gov.iti.jets.restapi.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import gov.iti.jets.domain.models.*;
import gov.iti.jets.restapi.controllers.CategoryController;
import gov.iti.jets.restapi.controllers.OrderController;
import gov.iti.jets.restapi.controllers.ProductController;
import gov.iti.jets.restapi.controllers.UserController;
import gov.iti.jets.restapi.dtos.category.CategoryDto;
import gov.iti.jets.restapi.dtos.category.CategoryProduct;
import gov.iti.jets.restapi.dtos.product.ProductResponseDto;
import gov.iti.jets.restapi.dtos.user.LineItemDto;
import gov.iti.jets.restapi.dtos.user.OrderDto;
import gov.iti.jets.restapi.dtos.user.UserDto;
import jakarta.ws.rs.core.UriInfo;

public class RestMapper {
    
    public static ProductResponseDto mapProductToProductResponse(Product inputProduct, UriInfo uriInfo){

        ProductResponseDto mappedProduct = new ProductResponseDto(inputProduct.getId(), inputProduct.getName(), inputProduct.getDescription(), inputProduct.getPrice());
        
        mappedProduct.setCategories( categoryMapper(inputProduct.getCategories(), uriInfo));


        String selfUri = uriInfo.getAbsolutePathBuilder().path(ProductController.class).path(Long.toString(mappedProduct.getId())).build().toString();

        mappedProduct.addLink(selfUri, "self");

        return mappedProduct;
    }

    public static List<CategoryDto> categoryMapper( Set<Category> inputCategories, UriInfo uriInfo){

        List<CategoryDto> mappedCategories = new ArrayList<>();
        inputCategories.forEach(category ->{
            String selfUri = uriInfo.getBaseUriBuilder().path( CategoryController.class).path(Long.toString( category.getId())).build().toString();
            CategoryDto mappedCategoryDto = new CategoryDto(category.getId(), category.getName());
            mappedCategoryDto.addLink( selfUri, "products" );
            mappedCategories.add( mappedCategoryDto );
        });
        return mappedCategories;
    }

    public static List<CategoryProduct> categoryProductMapper( List<Product> products, UriInfo uriInfo ) {
        List<CategoryProduct> categoryProducts = new ArrayList<>();

         products.forEach( product -> {

            String selfUri = uriInfo.getAbsolutePathBuilder().path(ProductController.class).path(Long.toString(product.getId())).build().toString();

             CategoryProduct categoryProduct =  new CategoryProduct( product.getId(), product.getDescription(), product.getName() );
             categoryProduct.addLink( selfUri, "self" );

            categoryProducts.add(categoryProduct);
    });

    return categoryProducts;
}

public static UserDto mapUserToUserDto( User user, UriInfo uriInfo){
        UserDto userDto = new UserDto(user);
        addSelfUriToUser( userDto, uriInfo );
        addCartUriToUser( userDto, uriInfo );
        return userDto;
}

private static void addSelfUriToUser(UserDto user, UriInfo uriInfo){
    String selfUri = uriInfo.getAbsolutePathBuilder().path( UserController.class).path(Long.toString(user.getId())).build().toString();

    user.addLink( selfUri, "self" );

}


    private static void addCartUriToUser(UserDto user, UriInfo uriInfo){
        String cartUri = uriInfo.getAbsolutePathBuilder().path( UserController.class).path(Long.toString(user.getId())).path( "cart" ).build().toString();

        user.addLink( cartUri, "User cart" );

    }



    public static List<LineItemDto> mapLineItemsToLineItemDtos( Set<CartLineItem> lineItems){
        return lineItems.stream().map( lineItem -> new LineItemDto(lineItem.getId(), lineItem.getProduct().getId(), lineItem.getQuantity()) ).collect( Collectors.toList());
    }

    public static List<LineItemDto> mapLineItemsToLineItemDtos( List<OrderLineItem> lineItems){
        return lineItems.stream().map( lineItem -> new LineItemDto(lineItem.getId(), lineItem.getProduct().getId(), lineItem.getQuantity()) ).collect( Collectors.toList());
    }


    public static OrderDto mapOrderToOrderDto( Order order, UriInfo uriInfo){
        OrderDto orderDto = new OrderDto();
        orderDto.setId( order.getId() );
        orderDto.setLineItemDtoList( mapLineItemsToLineItemDtos( order.getLineItems() ) );
        orderDto.setStatus( order.getStatus() );
        String selfUri = uriInfo.getAbsolutePathBuilder().path( OrderController.class).path(Long.toString(order.getId())).build().toString();

        orderDto.addLink( selfUri, "self" );



        return orderDto;
    }

}
