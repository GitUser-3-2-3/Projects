package com.jwt.security.repo;

import com.jwt.security.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InstRepo extends JpaRepository<Institution, Integer> {

    Institution findByInstName(String instName);

    Institution findByInstEmail(String instEmail);

    @Transactional
    List<Institution> findAllByInstCity(String instCity);

    @Transactional
    void deleteByInstEmail(String instEmail);
}
