package gov.iti.jets.filtersAndInterceptors;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TestingResponseFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext arg0, ContainerResponseContext arg1) throws IOException {

        System.out.println("request filter");
    }
    
}
