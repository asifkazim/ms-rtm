package az.asifkazim.service;

import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ExamService {


    ExamResponseDto createExam(MultipartFile answers, MultipartFile results, ExamRequestDto examRequestDto);
}
