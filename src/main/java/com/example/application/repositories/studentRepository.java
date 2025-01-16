package com.example.application.repositories;

import com.example.application.models.studentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository<studentModel, Integer> {
}
