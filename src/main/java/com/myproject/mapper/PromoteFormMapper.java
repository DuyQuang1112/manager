package com.myproject.mapper;

import com.myproject.dto.PromoteFormDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "PromoteFormMapper",
        classes = @ConstructorResult(
                targetClass = PromoteFormDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "employee_id", type = Integer.class),
                        @ColumnResult(name = "leader_id", type = Integer.class),
                        @ColumnResult(name = "promote_date", type = LocalDate.class),
                        @ColumnResult(name = "promote_times", type = Integer.class),
                        @ColumnResult(name = "promote_reason", type = String.class),
                        @ColumnResult(name = "present_position", type = String.class),
                        @ColumnResult(name = "old_position", type = String.class),
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
public class PromoteFormMapper {
    @Id
    private Integer id;
}
