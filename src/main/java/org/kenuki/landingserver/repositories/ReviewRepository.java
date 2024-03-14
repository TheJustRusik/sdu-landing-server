package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

}
