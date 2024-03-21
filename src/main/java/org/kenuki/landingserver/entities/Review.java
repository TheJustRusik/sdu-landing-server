package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private Boolean visible;

    public Review(String author, String review, Boolean visible){
        this.author = author;
        this.review = review;
        this.visible = visible;
    }
}
