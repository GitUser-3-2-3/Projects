package com.jwt.security.repo;

import com.jwt.security.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstRepo extends JpaRepository<Institution, Integer> {

    Institution findByInstName(String instName);

    Institution findByInstEmail(String instEmail);

    Boolean existsByInstEmail(String instEmail);

    List<Institution> findAllByInstCity(String instCity);

    int deleteByInstEmail(String instEmail);
}
