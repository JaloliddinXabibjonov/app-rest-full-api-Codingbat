package mypackage.apprestfullapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskDto {

    @NotNull(message = "Savol bo`sh bo`lmasligi kerak")
    private String question;

    private String solution;
    private String hint;

    @NotNull(message = "Bo`lim bo`sh bo`lmasligi kerak")
    private int departmentId;

}
