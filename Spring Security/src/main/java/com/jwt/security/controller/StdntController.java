package com.jwt.security.controller;

import com.jwt.security.model.Student;
import com.jwt.security.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StdntController {

    private final StudentService stdntService;

    @Autowired
    public StdntController(StudentService stdntService) {
        this.stdntService = stdntService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        return stdntService.addStudent(student) != null
            ? new ResponseEntity<>(student, HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = stdntService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/city/{stdntRes}")
    public ResponseEntity<List<Student>> getAllStdntByCity(@PathVariable("stdntRes") String stdntRes) {
        List<Student> students = stdntService.getAllStdntByCity(stdntRes);

        return students.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/name/{stdntName}")
    public ResponseEntity<List<Student>> getStdntByName(@PathVariable("stdntName") String stdntName) {
        List<Student> students = stdntService.getStdntByName(stdntName);

        return students.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/email/{stdntEmail}")
    public ResponseEntity<Student> getStdntByEmail(@PathVariable("stdntEmail") String stdntEmail) {
        Student student = stdntService.getStdntByEmail(stdntEmail);

        return student == null
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/dob/{dateOfBirth}")
    public ResponseEntity<List<Student>> getStdntByDob(@PathVariable("dateOfBirth") String dateOfBirth) {
        List<Student> students = stdntService.getStdntByDob(dateOfBirth);

        return students.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{stdntEmail}")
    public ResponseEntity<String> deleteStudent(@PathVariable("stdntEmail") String stdntEmail) {
        return stdntService.deleteStdntByEmail(stdntEmail)
            ? new ResponseEntity<>("Student deleted successfully", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}







