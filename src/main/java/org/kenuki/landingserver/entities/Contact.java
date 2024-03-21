package org.kenuki.landingserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @Column(name = "name")
    private String key;

    @Column(nullable = false, name = "val")
    private String value;
}
