package org.kenuki.landingserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Contact {
    @Id
    private String key;

    @Column(nullable = false)
    private String value;
}
