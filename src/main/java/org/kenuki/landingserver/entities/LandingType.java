package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "landing_types")
public class LandingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;
}
