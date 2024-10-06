package com.jwt.security.repo;

import com.jwt.security.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstRepo extends JpaRepository<Institution, Integer> {

    Institution findByInstitutionName(String instName);

    Institution findByInstitutionEmail(String instEmail);
}
