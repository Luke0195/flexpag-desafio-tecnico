package br.com.flexpag.app.services.appointment.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }
}
