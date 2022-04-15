package gov.iti.jets.filtersAndInterceptors;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class TestingRequestPreFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext arg0) throws IOException {
        
        System.out.println("request filter");
        
    }
    
}
