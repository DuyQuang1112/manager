package com.myproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.myproject.constant.MessageErrorConst.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInfoDTO {
    private Integer id;
    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = CODE_NOT_BLANK)
    private String code;

    @NotBlank(message = GENDER_NOT_BLANK)
    private String gender;

    @NotNull(message = DOB_NOT_BLANK)
    private LocalDate dateOfBirth;

    @NotBlank(message = ADDRESS_NOT_BLANK)
    private String address;

    @NotBlank(message = TEAM_NOT_BLANK)
    private String team;

    private String image;

    @NotBlank(message = ID_NUMBER_NOT_BLANK)
    private String identificationNumber;

    @NotBlank(message = PHONE_NOT_BLANK)
    private String phoneNumber;

    @Email(message = INVALID_EMAIL)
    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

}
