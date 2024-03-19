package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;
@Data
@AllArgsConstructor
public class PortfolioResponseDTO {
    private String title;
    private String description;
    private Resource image;
}
