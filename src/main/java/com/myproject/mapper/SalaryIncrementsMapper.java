package com.myproject.mapper;

import com.myproject.dto.SalaryIncrementsDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "SalaryIncrementsMapper",
        classes = @ConstructorResult(
                targetClass = SalaryIncrementsDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "employee_id", type = Integer.class),
                        @ColumnResult(name = "leader_id", type = Integer.class),
                        @ColumnResult(name = "salary_increase_date", type = LocalDate.class),
                        @ColumnResult(name = "salary_increase_times", type = Integer.class),
                        @ColumnResult(name = "salary_increase_reason", type = String.class),
                        @ColumnResult(name = "employee_rank", type = String.class),
                        @ColumnResult(name = "note", type = String.class),
                        @ColumnResult(name = "submission_date", type = LocalDate.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "additional_require", type = String.class)
                }
        )
)
@Entity
public class SalaryIncrementsMapper {
    @Id
    private Integer id;
}
