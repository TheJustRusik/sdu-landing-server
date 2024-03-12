package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Entity
@Data
@Table(name = "contents")
public class Content {
    @Id
    private String id;

    @Column(nullable = false)
    private String content;
}
