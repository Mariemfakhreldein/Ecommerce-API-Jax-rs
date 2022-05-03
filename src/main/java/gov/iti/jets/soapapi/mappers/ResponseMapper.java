package gov.iti.jets.soapapi.mappers;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Order;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.restapi.dtos.user.LineItemDto;
import gov.iti.jets.restapi.dtos.user.OrderDto;
import gov.iti.jets.soapapi.dtos.SoapCategoryDto;
import gov.iti.jets.soapapi.dtos.SoapLineItemDto;
import gov.iti.jets.soapapi.dtos.SoapOrderDto;
import gov.iti.jets.soapapi.dtos.SoapProductDto;

import java.util.stream.Collectors;

public class ResponseMapper {

    public static SoapProductDto mapProductToProductResponse( Product product ){
        return new SoapProductDto( product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategories().stream().map( ResponseMapper::mapCategoryToSoapCategoryDto ).collect( Collectors.toList()) );
    }

    public static SoapCategoryDto mapCategoryToSoapCategoryDto( Category category ){
        return new SoapCategoryDto( category.getId(), category.getName() );
    }


    public static SoapOrderDto mapOrderToSoapOrderDto( Order order){
        SoapOrderDto orderDto = new SoapOrderDto();
        orderDto.setId( order.getId() );

        orderDto.setLineItemDtoList( order.getLineItems().stream().map( SoapLineItemDto::new ).collect( Collectors.toList()) );

        orderDto.setStatus( order.getStatus() );

        return orderDto;
    }

}
