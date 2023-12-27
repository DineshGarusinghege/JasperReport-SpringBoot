package com.sample.nutritionreport.controller;

import com.sample.nutritionreport.processor.NutritionReportApplication;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequiredArgsConstructor
public class NutritionConroller {
    private final NutritionReportApplication nutritionReportApplication;

    @GetMapping("/report")
    public ResponseEntity getNutritionReport() throws JRException{
        ByteArrayOutputStream reportSteam=nutritionReportApplication.generateReport();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);

        return  new ResponseEntity(reportSteam.toByteArray(),httpHeaders, HttpStatus.OK);
    }

}
