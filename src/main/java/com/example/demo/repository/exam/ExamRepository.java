package com.example.demo.repository.exam;

import com.example.demo.entity.exam.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
ExamRepository extends JpaRepository<Examination, Long> {
}
