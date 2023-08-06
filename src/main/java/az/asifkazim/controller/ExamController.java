package az.asifkazim.controller;

import az.asifkazim.model.dto.FileDto;
import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;


    @PostMapping
    public ResponseEntity<ExamResponseDto> createExam(
            @RequestBody ExamRequestDto examRequestDto) {

        ExamResponseDto exam = examService.createExam(examRequestDto);
        return ResponseEntity.status(201).body(exam);
    }

    @PostMapping(value = "/{id}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExamResponseDto> uploadExamsFiles(@PathVariable Long id,
                                                            @RequestParam("answers") MultipartFile answers,
                                                            @RequestParam("results") MultipartFile results) {

        ExamResponseDto exam = examService.uploadExamsFiles(id, answers, results);
        return ResponseEntity.status(200).body(exam);
    }

    @PostMapping(value = "/{id}/calculateResults")
    public ResponseEntity calculateResults(@PathVariable Long id) throws IOException {

        examService.calculateResults(id);
        return ResponseEntity.ok(null);
    }






}
