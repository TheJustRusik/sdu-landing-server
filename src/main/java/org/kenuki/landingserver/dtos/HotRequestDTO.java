package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotRequestDTO {
    private String name;
    private String email;
    private String phone_number;
    private Long   landing_type;
}
