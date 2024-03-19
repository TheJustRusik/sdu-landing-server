package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByImage(String image_name);
    boolean existsByImage(String image_name);
}