package com.example.application.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SCORE_CARD")
public class scoreCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SUBJECT_NAME")
    private String subject_name;

    @Column(name = "TOTAL_MARKS")
    private double total_marks;

    @Column(name = "MARKS_OBTAINED")
    private double marks_obtained;

    @ManyToOne
    @JoinColumn(name = "roll_number", nullable = false)
    private studentModel student;


    public scoreCardModel() {
    }

    public scoreCardModel(Integer id, String subject_name, double total_marks, double marks_obtained, studentModel student) {
        this.id = id;
        this.subject_name = subject_name;
        this.total_marks = total_marks;
        this.marks_obtained = marks_obtained;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public double getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(double total_marks) {
        this.total_marks = total_marks;
    }

    public double getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(double marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public studentModel getStudent() {
        return student;
    }

    public void setStudent(studentModel student) {
        this.student = student;
    }
}
