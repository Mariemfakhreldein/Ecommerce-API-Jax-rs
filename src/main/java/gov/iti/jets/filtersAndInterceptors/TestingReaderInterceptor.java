package gov.iti.jets.filtersAndInterceptors;

import java.io.IOException;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

@Provider
public class TestingReaderInterceptor implements ReaderInterceptor{

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext arg0) throws IOException, WebApplicationException {
        System.out.println("reader interceptor");
        arg0.proceed();
        return null;
    }
    
}
