package az.asifkazim.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamResponseDto {

    private Long id;
    private String name;
    private String type;
    private boolean active;
    private String answersFile;
    private String resultsFile;
}
