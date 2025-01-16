package com.example.application.services;
import com.example.application.models.scoreCardModel;
import com.example.application.repositories.scoreCardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class scoreCardService {

    private final scoreCardRepository scoreCardRepository;
    private final DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    private scoreCardService(scoreCardRepository scoreCardRepository, DataSource dataSource) {
        this.scoreCardRepository = scoreCardRepository;
        this.dataSource = dataSource;
    }

    public List<scoreCardModel> getAllScoreCards(){
        entityManager.clear();
        return scoreCardRepository.findAll();
    }

    public List<Map<String, Object>>  getHighestScoresByStudent(){
        return scoreCardRepository.findHighsScoreByStudent();
    }

    public scoreCardModel saveScoreCard(scoreCardModel scoreCardModel){
        return scoreCardRepository.save(scoreCardModel);
    }


    public byte[] generateReport(String reportName){
        try {
            InputStream fileStream = getClass().getResourceAsStream("/reports/"+reportName+".jasper");

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(fileStream, parameters,dataSource.getConnection());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
