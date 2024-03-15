package org.kenuki.landingserver.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class PortfolioDTO {
    private String title;
    private String description;
    private MultipartFile image;
}
