package com.example.application.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class studentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLL_NUMBER")
    private Integer roll_number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "ADVISOR")
    private String advisor;

    @Column(name = "GRADING_PERIOD")
    private String grading_period;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<scoreCardModel> scoreCards;


    public studentModel() {
    }

    public studentModel(Integer roll_number, String name, String year, String advisor, String grading_period, List<scoreCardModel> scoreCards) {
        this.roll_number = roll_number;
        this.name = name;
        this.year = year;
        this.advisor = advisor;
        this.grading_period = grading_period;
        this.scoreCards = scoreCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(Integer roll_number) {
        this.roll_number = roll_number;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getGrading_period() {
        return grading_period;
    }

    public void setGrading_period(String grading_period) {
        this.grading_period = grading_period;
    }

    public List<scoreCardModel> getScoreCards() {
        return scoreCards;
    }

    public void setScoreCards(List<scoreCardModel> scoreCards) {
        this.scoreCards = scoreCards;
    }
}
