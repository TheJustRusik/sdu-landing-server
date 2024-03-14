package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {

}