package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.ColdRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColdRequestRepository extends JpaRepository<ColdRequest, Long> {

}
