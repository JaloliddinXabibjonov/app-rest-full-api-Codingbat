package mypackage.apprestfullapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {

    @NotNull(message = "Dasturlash tili nomi bo`sh bo'lmasligi kerak")
    private String name;

}
