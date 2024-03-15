package org.kenuki.landingserver.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadImagePathException extends Exception{
    private String message;
    public BadImagePathException(String message){
        this.message = message;
    }
}
