package az.asifkazim.service;

import az.asifkazim.model.dto.FileDto;
import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExamService {


    ExamResponseDto createExam(ExamRequestDto examRequestDto);

    ExamResponseDto uploadExamsFiles(Long id, MultipartFile answers, MultipartFile results);

    void calculateResults(Long id) throws IOException;

    byte[] getFile(String fileName,String folder);
}
