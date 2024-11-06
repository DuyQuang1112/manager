package com.myproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationFormDTO {
    private Integer id;
    private Integer employeeId;
    private Integer leaderId;
    private Integer managerId;
    private LocalDate submissionDate;
    private String content;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private LocalDate additionalRequire;
    private String status;
    private String note;
    private LocalDate appointmentDate;
}
