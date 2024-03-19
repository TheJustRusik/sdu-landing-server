package org.kenuki.landingserver.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, columnDefinition = "bool default false")
    private Boolean checked;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LandingType landingType;

    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
}
