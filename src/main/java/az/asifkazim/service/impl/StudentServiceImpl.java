package az.asifkazim.service.impl;

import az.asifkazim.mapper.StudentMapper;
import az.asifkazim.model.dto.StudentDto;
import az.asifkazim.model.entity.Student;
import az.asifkazim.repository.StudentRepository;
import az.asifkazim.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public Student addStudent(StudentDto studentDto) {

        return studentRepository.save(studentMapper.dtoToEntity(studentDto));
    }
}
