package gov.iti.jets.api.exceptionmappers;

import gov.iti.jets.api.dtos.ExceptionMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerExceptionsMapper implements ExceptionMapper<MyCustomException>{

    @Override
    public Response toResponse(MyCustomException ex) {
        ex.getE().printStackTrace();
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ExceptionMessage(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage())).build();
    }
    
}
