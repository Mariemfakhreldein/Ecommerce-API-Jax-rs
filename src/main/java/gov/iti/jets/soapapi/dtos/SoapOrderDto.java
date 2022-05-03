package gov.iti.jets.soapapi.dtos;

import gov.iti.jets.domain.enums.Status;
import gov.iti.jets.restapi.dtos.user.LineItemDto;

import java.util.List;

public class SoapOrderDto {
    private int id;
    private Status status;
    private List<SoapLineItemDto> lineItemDtoList;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }

    public List<SoapLineItemDto> getLineItemDtoList() {
        return lineItemDtoList;
    }

    public void setLineItemDtoList( List<SoapLineItemDto> lineItemDtoList ) {
        this.lineItemDtoList = lineItemDtoList;
    }
}
