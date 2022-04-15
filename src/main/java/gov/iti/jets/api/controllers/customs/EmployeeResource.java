package gov.iti.jets.api.controllers.customs;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("employees")
public class EmployeeResource {
    @GET
    @Produces( "custom/type" )
    public Employee needsMsgBodyWriter() {
        Employee employee = new Employee(123, "amin", 10);
        return employee;
    }

    @POST
    @Consumes("custom/type")
    public void needsMsgBodyReader(Employee employee) {
        System.out.println(employee);
    }
}
