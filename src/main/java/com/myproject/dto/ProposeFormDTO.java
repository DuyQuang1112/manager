package com.myproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposeFormDTO  {
    private Integer id;
    private Integer employeeId;
    private Integer leaderId;
    private LocalDate eventDate;
    private String content;
    private String note;
    private String proposeType;
    private LocalDate submissionDate;
    private String status;
    private LocalDate acceptDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private String additionalRequire;
}
