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
        return response.containsKey(true)
            ? new ResponseEntity<>(response.get(true), HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Institution>> getAllInstitutions() {
        List<Institution> institutions = instService.getAllInstitutions();

        return institutions == null
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(institutions, HttpStatus.OK);

    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Institution>> getAllInstByCity(@PathVariable("city") String city) {
        List<Institution> institutions = instService.getAllInstByCity(city);

        return institutions.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(institutions, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Institution> getInstByName(@PathVariable("name") String instName) {
        Institution institution = instService.getInstByName(instName);

        return institution == null
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(institution, HttpStatus.OK);
    }

    @GetMapping("/email/{instEmail}")
    public ResponseEntity<Institution> getInstByEmail(@PathVariable("instEmail") String instEmail) {
        Institution institution = instService.getInstByEmail(instEmail);

        return institution == null
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(institution, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{instEmail}")
    public ResponseEntity<String> deleteInstitution(@PathVariable("instEmail") String instEmail) {
        return instService.deleteInstByEmail(instEmail)
            ? new ResponseEntity<>("Institution deleted successfully.", HttpStatus.OK)
            : new ResponseEntity<>(instEmail, HttpStatus.NOT_FOUND);
    }
}







