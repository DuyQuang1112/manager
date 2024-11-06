package com.myproject.mapper;

import com.myproject.dto.ProfileEndDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "ProfileEndMapper",
        classes = @ConstructorResult(
                targetClass = ProfileEndDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "registration_form_id", type = Integer.class),
                        @ColumnResult(name = "leader_id", type = Integer.class),
                        @ColumnResult(name = "end_date", type = LocalDate.class),
                        @ColumnResult(name = "end_reason", type = String.class),
                        @ColumnResult(name = "record_number", type = Integer.class),
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
public class ProfileEndMapper {
    @Id
    private Integer id;
}
