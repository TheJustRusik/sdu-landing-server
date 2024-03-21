package org.kenuki.landingserver.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageNotFoundException extends Exception{
    private String message;
    public ImageNotFoundException(String message){
        this.message = message;
    }
}
