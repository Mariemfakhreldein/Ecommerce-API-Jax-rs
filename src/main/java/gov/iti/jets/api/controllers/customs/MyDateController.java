package gov.iti.jets.api.controllers.customs;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path( "date" )
public class MyDateController {
    @GET
    @Path( "{dateParam}" )
    @Produces( MediaType.TEXT_PLAIN)
    public String getDateOfTheDay( @PathParam ( "dateParam" ) MyDateType dateParam){
        return dateParam.toString();
    }

}
