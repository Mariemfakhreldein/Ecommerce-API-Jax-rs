package gov.iti.jets.filtersAndInterceptors;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TestingRequestPostFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext arg0) throws IOException {
        System.out.println("post match request filter");     
    }
    
}
