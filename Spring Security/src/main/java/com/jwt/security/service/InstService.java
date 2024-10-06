package com.jwt.security.service;

import com.jwt.security.model.Institution;
import com.jwt.security.repo.InstRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<Boolean, Institution> addInstitution(final Institution inst) {
        if (inst != null) {
            return new HashMap<>(Map.of(true, instRepository.save(inst)));
        }
        return new HashMap<>(Map.of(false, new Institution()));
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

    public Map<Boolean, String> deleteInstitution(final String instEmail) {
        Institution inst = instRepository.findByInstEmail(instEmail);

        if (inst != null) {
            instRepository.deleteByInstEmail(instEmail);
            return new HashMap<>(Map.of(true, "Institution deleted successfully."));
        }
        return new HashMap<>(Map.of(false, instEmail));
    }
}








