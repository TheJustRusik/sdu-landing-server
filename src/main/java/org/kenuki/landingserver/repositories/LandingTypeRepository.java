package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.LandingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingTypeRepository extends JpaRepository<LandingType, Integer> {
}
