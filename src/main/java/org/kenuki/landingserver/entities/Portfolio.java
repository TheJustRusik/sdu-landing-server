package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "img_id")
    private Image image;
}
