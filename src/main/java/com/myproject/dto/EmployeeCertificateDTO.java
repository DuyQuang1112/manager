package com.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCertificateDTO {

    private Integer id;
    private String name;
    private LocalDate date_of_issue;
    private String content;
    private String major;
    private Integer employeeId;

}
