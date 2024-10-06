package com.jwt.security.controller;

import com.jwt.security.model.Institution;
import com.jwt.security.service.InstService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/institution")
public class InstController {

    private final InstService instService;

    @Autowired
    public InstController(InstService instService) {
        this.instService = instService;
    }

    @PostMapping
    public ResponseEntity<Institution> addInstitution(
        @RequestBody @Valid Institution institution
    ) {
        Map<Boolean, Institution> response = instService.addInstitution(institution);

        if (response.containsKey(true)) {
            return new ResponseEntity<>(response.get(true), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(institution, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Institution>> getAllInstitutions() {
        List<Institution> institutions = instService.getAllInstitutions();
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Institution>> getAllInstByCity(@PathVariable("city") String city) {
        List<Institution> institutions = instService.getAllInstByCity(city);
        if (institutions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Institution> getInstByName(@PathVariable("name") String name) {
        Institution institution = instService.getInstByName(name);
        if (institution != null) {
            return new ResponseEntity<>(institution, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Institution> getInstByEmail(@PathVariable("email") String email) {
        Institution institution = instService.getInstByEmail(email);
        if (institution != null) {
            return new ResponseEntity<>(institution, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Map<Boolean, String>> deleteInstitution(@PathVariable("email") String email) {
        Map<Boolean, String> response = instService.deleteInstitution(email);

        if (response.containsKey(true)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
