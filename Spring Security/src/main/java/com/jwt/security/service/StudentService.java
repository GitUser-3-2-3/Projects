package com.jwt.security.service;

import com.jwt.security.model.Institution;
import com.jwt.security.model.Student;
import com.jwt.security.repo.InstRepo;
import com.jwt.security.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo stdntRepository;
    private final InstRepo instRepository;

    @Autowired
    public StudentService(StudentRepo stdntRepository, InstRepo instRepository) {
        this.stdntRepository = stdntRepository;
        this.instRepository = instRepository;
    }

    @Transactional
    public Student addStudent(final Student student) {
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Student can't be null");
        }
        Institution inst = instRepository.findById(student.getInstitution().getInstId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        student.setStdntInstitute(inst.getInstName());
        student.setInstitution(inst);
        return stdntRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return stdntRepository.findAll();
    }

    public List<Student> getAllStdntByCity(String stdntRes) {
        return stdntRepository.findAllByStdntResidence(stdntRes);
    }

    public List<Student> getStdntByName(String stdntName) {
        return stdntRepository.findByStdntName(stdntName);
    }

    public Student getStdntByEmail(final String stdntEmail) {
        return stdntRepository.findByStdntEmail(stdntEmail);
    }

    public List<Student> getStdntByDob(String dateOfBirth) {
        return stdntRepository.findByDateOfBirth(dateOfBirth);
    }

    @Transactional
    public boolean deleteStdntByEmail(String stdntEmail) {
        return stdntRepository.existsByStdntEmail(stdntEmail)
            && stdntRepository.deleteByStdntEmail(stdntEmail) == 1;
    }
}







