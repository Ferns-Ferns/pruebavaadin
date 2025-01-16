package com.example.application.controllers;

import com.example.application.models.studentModel;
import com.example.application.repositories.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testController {

    @Autowired
    private studentRepository repository;

    @GetMapping("/students")
    public List<studentModel> getAllStudents(){
        return repository.findAll();
    }
}
