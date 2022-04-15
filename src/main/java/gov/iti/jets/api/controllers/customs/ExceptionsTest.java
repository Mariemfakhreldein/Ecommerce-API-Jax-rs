package gov.iti.jets.api.controllers.customs;

import javax.sql.rowset.serial.SerialException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;

@Path("exception")
public class ExceptionsTest {
    @GET
    public void throwTheException(){
        throw new WebApplicationException();
    }
}
