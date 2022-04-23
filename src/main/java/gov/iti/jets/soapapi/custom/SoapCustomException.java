package gov.iti.jets.soapapi.custom;

public class SoapCustomException extends Exception{
    public SoapCustomException( String message, Throwable cause ) {
        super( message, cause );
    }
}
