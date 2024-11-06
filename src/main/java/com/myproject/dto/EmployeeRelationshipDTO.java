package com.myproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.myproject.constant.MessageErrorConst.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRelationshipDTO {

    private Integer id;
    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = GENDER_NOT_BLANK)
    private String gender;

    @NotNull(message = DOB_NOT_BLANK)
    private LocalDate dateOfBirth;

    @NotBlank(message = ID_NUMBER_NOT_BLANK)
    private String identificationNumber;

    @NotBlank(message = "Relationship can not be null or empty")
    private String relationship;

    @NotBlank(message = ADDRESS_NOT_BLANK)
    private String address;

    @NotNull(message = EMPLOYEE_ID_NOT_NULL)
    private Integer employeeId;

}
