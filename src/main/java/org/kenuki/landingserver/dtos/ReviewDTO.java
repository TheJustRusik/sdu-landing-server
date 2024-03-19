package org.kenuki.landingserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private String author;
    private String review;
    private Boolean visible;
}
