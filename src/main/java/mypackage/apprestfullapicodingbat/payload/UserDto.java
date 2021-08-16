package mypackage.apprestfullapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "username bo`sh bo`lmasligi lozim")
    private String username;

    @NotNull(message = "parol bo`sh bo`lmasligi lozim")
    private String password;

}
