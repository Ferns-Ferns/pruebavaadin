package com.example.application.controllers;
import com.example.application.services.scoreCardService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class scoreCardController {

    private final scoreCardService scoreCardService;

    public scoreCardController(com.example.application.services.scoreCardService scoreCardService) {
        this.scoreCardService = scoreCardService;
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateScoreCardReport(){
        try {
            byte[] report = scoreCardService.generateReport("ReportVaadin");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
