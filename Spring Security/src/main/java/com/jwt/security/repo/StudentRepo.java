package com.jwt.security.repo;

import com.jwt.security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    List<Student> findByStudentName(String studentName);

    Student findByStudentEmail(String studentEmail);

    List<Student> findByDateOfBirth(LocalDate dateOfBirth);

    List<Student> findByZipcode(Integer zipcode);

    List<Student> findStudentByInstitutionContaining(String instName);
}
