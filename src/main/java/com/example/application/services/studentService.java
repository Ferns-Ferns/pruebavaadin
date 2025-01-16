package com.example.application.services;

import com.example.application.models.studentModel;
import com.example.application.repositories.studentRepository;
import com.nimbusds.jose.shaded.gson.JsonArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {

    studentRepository studentRepository;

    private studentService(studentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<studentModel> getAllStudents(){
        return studentRepository.findAll();
    }

    public JsonArray getAllStudentsGraph(){
        JsonArray studentsJson = new JsonArray();

        return  studentsJson;
    }

    public studentModel saveStudent(studentModel student){
        return studentRepository.save(student);
    }


}
