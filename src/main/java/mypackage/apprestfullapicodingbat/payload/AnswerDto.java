package mypackage.apprestfullapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    @NotNull(message = "Javobni tekshirish qismi bo`sh bo`lmasligi kerak")
    private String textCheckAnswer;

    @NotNull(message = "Javobni to`g'ri ekanligini aniqlash qismi bo`sh bo`lmasligi kerak")
    private boolean success;

    private String description;

    private Integer userId;

    @NotNull(message = "Savol qismi bo`sh bo`lmasligi kerak")
    private Integer taskId;
}
