package gov.iti.jets.restapi.dtos.order;

import gov.iti.jets.domain.enums.Status;

import java.io.Serializable;

public class OrderStatusRequest implements Serializable {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }
}
