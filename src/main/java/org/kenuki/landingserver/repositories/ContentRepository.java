package org.kenuki.landingserver.repositories;

import org.kenuki.landingserver.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContentRepository extends JpaRepository<Content, String> {

}
