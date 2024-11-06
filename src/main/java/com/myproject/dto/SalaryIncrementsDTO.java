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
public class SalaryIncrementsDTO {
    private Integer id;
    private Integer employeeId;
    private Integer leaderId;
    private LocalDate salaryIncreaseDate;
    private Integer salaryIncreaseTimes;
    private String salaryIncreaseReason;
    private String employeeRank;
    private String note;
    private LocalDate submissionDate;
    private String status;
    private LocalDate acceptDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private String additionalRequire;
}
