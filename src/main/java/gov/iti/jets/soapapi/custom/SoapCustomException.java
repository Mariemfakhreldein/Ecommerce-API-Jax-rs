package gov.iti.jets.soapapi.custom;

public class SoapCustomException extends RuntimeException{
    public SoapCustomException( String message, Throwable cause ) {
        super( message, cause );
    }
}
