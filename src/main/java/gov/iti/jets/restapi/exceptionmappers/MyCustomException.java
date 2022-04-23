package gov.iti.jets.restapi.exceptionmappers;

public class MyCustomException extends Throwable{
    private Exception e;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public Exception getE() {
        return e;
    }

    public void setE( Exception e ) {
        this.e = e;
    }

    public MyCustomException( Exception e , String message){
        this.e = e;
        this.message = message;
    }
}
