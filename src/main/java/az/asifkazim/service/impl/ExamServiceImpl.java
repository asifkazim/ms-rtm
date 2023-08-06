package az.asifkazim.service.impl;

import az.asifkazim.error.EntityNotFoundException;
import az.asifkazim.mapper.ExamMapper;
import az.asifkazim.model.dto.StudentDto;
import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.model.entity.Exam;
import az.asifkazim.repository.ExamRepository;
import az.asifkazim.service.ExamService;
import az.asifkazim.service.FileService;
import az.asifkazim.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final ExamRepository examRepository;
    private final FileService fileService;
    private final StudentService studentService;

    @Value("${minio.answers-folder}")
    private String answersFolder;

    @Value("${minio.results-folder}")
    private String resultsFolder;

    @Override
    public ExamResponseDto createExam(ExamRequestDto examRequestDto) {
        Exam exam = examRepository.save(examMapper.dtoToEntity(examRequestDto));
        return examMapper.entityToDto(examRepository.save(exam));

    }

    @Override
    public ExamResponseDto uploadExamsFiles(Long id, MultipartFile answers, MultipartFile results) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Exam.class, id));
        if (exam.getAnswersFile() == null || exam.getResultsFile() == null) {
            String answersFileName = fileService.uploadFile(answers, answersFolder);
            String resultsFileName = fileService.uploadFile(results, resultsFolder);
            exam.setResultsFile(resultsFileName);
            exam.setAnswersFile(answersFileName);
            exam = examRepository.save(exam);

        }
        return examMapper.entityToDto(exam);

    }

    @Override
    public void calculateResults(Long id) throws IOException {
        //get files from minio
        Exam exam = examRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Exam.class, id));
        byte[] answers = getFile(exam.getAnswersFile(), answersFolder);
        byte[] results = getFile(exam.getResultsFile(), answersFolder);

        //divide byte answers
        splitAnswers(answers);
        splitResults(results);


    }



    @Override
    public byte[] getFile(String fileName, String folder) {
        return fileService.getFile(fileName, folder);
    }

    private void splitAnswers(byte[] answers) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(answers);
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            //split the line List
            System.out.println(line);
        }
    }

    private List<StudentDto> splitResults(byte[] results) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(results);
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String line;
        List<StudentDto> students = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            StudentDto studentDto = StudentDto.builder()
                    .name(line.substring(0,12))
                    .surname(line.substring(12,24))
                    .code(Long.valueOf(line.substring(24,29)))
                    .clazz(line.substring(29,30))
                    .variant(line.substring(30,31))
                    .answers(line.substring(31,101))
                            .build();
            students.add(studentDto);
            studentService.addStudent(studentDto);
        }

        return students;
    }




}
