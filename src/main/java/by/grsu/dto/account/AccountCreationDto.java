package by.grsu.dto.account;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDto {
    private String login;
    private String password;
    private String firstname;
    private String surname;
    private String fatherName;
}
