package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColdRequestDTO {
    private String name;
    private String phone;
}
