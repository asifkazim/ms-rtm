package az.asifkazim.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamRequestDto {

    private String name;
    private String type;
    private boolean active;
}
