package gov.iti.jets.soapapi.mappers;

import gov.iti.jets.domain.models.Product;
import gov.iti.jets.soapapi.dtos.SoapProductDto;

public class ResponseMapper {

    public static SoapProductDto mapProductToProductResponse( Product product ){
        return new SoapProductDto( product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
