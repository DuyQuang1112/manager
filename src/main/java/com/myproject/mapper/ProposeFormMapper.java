package com.myproject.mapper;

import com.myproject.dto.ProposeFormDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "ProposeFormMapper",
        classes = @ConstructorResult(
                targetClass = ProposeFormDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "employee_id", type = Integer.class),
                        @ColumnResult(name = "leader_id", type = Integer.class),
                        @ColumnResult(name = "event_date", type = LocalDate.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "note", type = String.class),
                        @ColumnResult(name = "propose_type", type = String.class),
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
public class ProposeFormMapper {
    @Id
    private Integer id;
}
