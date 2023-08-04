package az.asifkazim.service.impl;

import az.asifkazim.mapper.ExamMapper;
import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.model.entity.Exam;
import az.asifkazim.repository.ExamRepository;
import az.asifkazim.service.ExamService;
import az.asifkazim.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final ExamRepository examRepository;
    private final FileService fileService;

    @Value("${minio.answers-folder}")
    private String answersFolder;

    @Value("${minio.results-folder}")
    private String resultsFolder;

    @Override
    public ExamResponseDto createExam(MultipartFile answers, MultipartFile results, ExamRequestDto examRequestDto) {
        Exam exam = examRepository.save(examMapper.dtoToEntity(examRequestDto));
        String answersFileName = fileService.uploadFile(answers, answersFolder);
        String resultsFileName = fileService.uploadFile(results, resultsFolder);
        exam.setAnswersFile(answersFileName);
        exam.setResultsFile(resultsFileName);
        return examMapper.entityToDto(examRepository.save(exam));

    }
}
