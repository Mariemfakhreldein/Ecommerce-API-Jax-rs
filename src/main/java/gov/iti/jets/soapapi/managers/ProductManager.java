package gov.iti.jets.soapapi.managers;

import gov.iti.jets.domain.models.Product;
import gov.iti.jets.domain.services.ProductService;
import gov.iti.jets.soapapi.custom.SoapCustomException;
import gov.iti.jets.soapapi.dtos.SoapProductDto;
import gov.iti.jets.soapapi.mappers.ResponseMapper;
import jakarta.jws.WebService;

import java.util.List;
import java.util.stream.Collectors;

@WebService
public class ProductManager {
    public List<SoapProductDto> getAllProducts() throws SoapCustomException {

        try {
            List<Product> productList = ProductService.getAllProducts();

            List<SoapProductDto> productResponseList = productList.stream()
                        .map( ResponseMapper::mapProductToProductResponse )
                            .collect( Collectors.toList() );

            return productResponseList;

        }catch ( Exception e ){
            e.printStackTrace();
            throw new SoapCustomException("Couldn't load products", e);
        }
    }


    public void addNewProduct( SoapProductDto productToAdd) throws SoapCustomException {
        try{
            ProductService.addNewProduct( new Product(productToAdd.getName(), productToAdd.getDescription(), productToAdd.getPrice()) );
        }catch ( Exception e ){
            throw new SoapCustomException( "Couldn't add product", e );
        }
    }




}
