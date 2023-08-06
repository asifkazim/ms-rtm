package az.asifkazim.service;

import az.asifkazim.model.dto.StudentDto;
import az.asifkazim.model.entity.Student;

public interface StudentService {
    Student addStudent(StudentDto studentDto);
}
