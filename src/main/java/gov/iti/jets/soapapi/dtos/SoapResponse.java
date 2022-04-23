package gov.iti.jets.soapapi.dtos;

public class SoapResponse {
    private String message;

    public SoapResponse( String message ) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}
