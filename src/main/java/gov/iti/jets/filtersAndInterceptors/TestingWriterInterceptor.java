package gov.iti.jets.filtersAndInterceptors;

import java.io.IOException;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;


@Provider
public class TestingWriterInterceptor implements WriterInterceptor{

    @Override
    public void aroundWriteTo(WriterInterceptorContext arg0) throws IOException, WebApplicationException {

        System.out.println("writer interceptor");
        arg0.proceed();
    }
    
}
