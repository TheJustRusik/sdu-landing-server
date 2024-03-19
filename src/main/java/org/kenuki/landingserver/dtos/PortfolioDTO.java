package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PortfolioDTO implements Serializable {
    private String title;
    private String description;
    private MultipartFile image;
}
