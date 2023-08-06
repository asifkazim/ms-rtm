package az.asifkazim.mapper;

import az.asifkazim.model.dto.StudentDto;
import az.asifkazim.model.dto.request.ExamRequestDto;
import az.asifkazim.model.dto.response.ExamResponseDto;
import az.asifkazim.model.entity.Exam;
import az.asifkazim.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {


    Student dtoToEntity(StudentDto studentDto);
}
