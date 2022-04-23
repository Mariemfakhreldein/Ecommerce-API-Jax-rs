package gov.iti.jets.soapapi.mappers;

import gov.iti.jets.domain.models.Category;
import gov.iti.jets.domain.models.Product;
import gov.iti.jets.soapapi.dtos.SoapProductCategory;
import gov.iti.jets.soapapi.dtos.SoapProductDto;

import java.util.stream.Collectors;

public class ResponseMapper {

    public static SoapProductDto mapProductToProductResponse( Product product ){
        return new SoapProductDto( product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategories().stream().map( ResponseMapper::mapCategoryToProductCategory ).collect( Collectors.toList()) );
    }

    public static SoapProductCategory mapCategoryToProductCategory( Category category ){
        return new SoapProductCategory( category.getId(), category.getName() );
    }

}
