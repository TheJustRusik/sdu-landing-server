package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestDTO {
    private String name;
    private String email;
    private String phone_number;
}
