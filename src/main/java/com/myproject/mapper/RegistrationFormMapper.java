package com.myproject.mapper;

import com.myproject.dto.RegistrationFormDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "RegistrationFormMapper",
        classes = @ConstructorResult(
                targetClass = RegistrationFormDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "employee_id", type = Integer.class),
                        @ColumnResult(name = "leader_id", type = Integer.class),
                        @ColumnResult(name = "manager_id", type = Integer.class),
                        @ColumnResult(name = "submission_date", type = LocalDate.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "additional_require", type = LocalDate.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "note", type = String.class),
                        @ColumnResult(name = "appointment_date", type = LocalDate.class)
                }
        )
)
@Entity
public class RegistrationFormMapper {
    @Id
    private Integer id;
}
