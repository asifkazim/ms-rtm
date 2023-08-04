package az.asifkazim.controller;

import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ExamResponseDto> createExam(@RequestParam("answers") MultipartFile answers,
                                                     @RequestParam("results") MultipartFile results,
                                                     @RequestBody ExamRequestDto examRequestDto) {

        ExamResponseDto exam  = examService.createExam(answers,results,examRequestDto);
        return ResponseEntity.status(201).body(exam);
    }

    @GetMapping
    public String test() {
            return "Hello World!";
    }


}
