package gov.iti.jets.api.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class XmlExceptionsMapper implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.ok(throwable.getMessage()).build();
    }
    
}
