package org.kenuki.landingserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    private String author;
    @Column(nullable = false)
    private String review;
}
