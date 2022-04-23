package gov.iti.jets.api.dtos;

public class ExceptionMessage {

    private int status;
    private String message;
    private String cause;

    
    public ExceptionMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public ExceptionMessage() {
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ExceptionMessage [message=" + message + ", status=" + status + "]";
    }
    
    
    
}
