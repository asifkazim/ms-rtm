package az.asifkazim.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String surname;
    private Long code;
    private String clazz;
    private String variant;
    private String answers;
}
