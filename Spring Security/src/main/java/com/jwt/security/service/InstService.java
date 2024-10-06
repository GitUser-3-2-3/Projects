package com.jwt.security.service;

import com.jwt.security.model.Institution;
import com.jwt.security.repo.InstRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InstService {

    private final InstRepo instRepository;

    @Autowired
    public InstService(InstRepo instRepository) {
        this.instRepository = instRepository;
    }

    @Transactional
    public Map<Boolean, Institution> addInstitution(final Institution inst) {
        if (inst == null) {
            return new HashMap<>(Map.of(false, new Institution()));
        }
        return new HashMap<>(Map.of(true, instRepository.save(inst)));
    }

    public List<Institution> getAllInstitutions() {
        return instRepository.findAll();
    }

    public List<Institution> getAllInstByCity(String instCity) {
        return instRepository.findAllByInstCity(instCity);
    }

    public Institution getInstByName(String instName) {
        return instRepository.findByInstName(instName);
    }

    public Institution getInstByEmail(final String instEmail) {
        return instRepository.findByInstEmail(instEmail);
    }

    @Transactional
    public boolean deleteInstByEmail(final String instEmail) {
        return instRepository.existsByInstEmail(instEmail)
            && instRepository.deleteByInstEmail(instEmail) == 1;
    }
}








