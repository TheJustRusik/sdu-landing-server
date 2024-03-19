package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private String image;
}
