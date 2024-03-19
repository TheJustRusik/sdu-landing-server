package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByVisibleIsTrue();
}
