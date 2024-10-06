package com.jwt.security.repo;

import com.jwt.security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    List<Student> findByStdntName(String stdntName);

    Student findByStdntEmail(String studentEmail);

    List<Student> findByDateOfBirth(LocalDate dateOfBirth);

    List<Student> findByStdntResidence(String stdntResidence);
}
