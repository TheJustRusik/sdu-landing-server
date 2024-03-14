package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cold_requests")
public class ColdRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String name;
}
