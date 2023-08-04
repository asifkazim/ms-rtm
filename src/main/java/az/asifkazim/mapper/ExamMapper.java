package az.asifkazim.mapper;

import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.model.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExamMapper {


    ExamResponseDto entityToDto(Exam exam);


    Exam dtoToEntity(ExamRequestDto examRequestDto);
}
