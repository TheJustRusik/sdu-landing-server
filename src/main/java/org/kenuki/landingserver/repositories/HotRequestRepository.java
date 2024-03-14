package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.HotRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotRequestRepository extends JpaRepository<HotRequest, Long> {

}
