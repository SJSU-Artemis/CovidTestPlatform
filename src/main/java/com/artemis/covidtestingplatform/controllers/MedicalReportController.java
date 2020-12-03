package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.MedicalReport;
import com.artemis.covidtestingplatform.services.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/medical-report")
@CrossOrigin
public class MedicalReportController {
    @Autowired
    MedicalReportService medicalReportService;

    @PostMapping
    public MedicalReport save(@RequestBody MedicalReport medicalReport){
        return medicalReportService.save(medicalReport);
    }

    @PostMapping("/upload")
    public List<String> saveFile(@RequestPart(value="file") MultipartFile[] files){
        return medicalReportService.saveFile(files);
    }
}
