package com.myproject.mapper;

import com.myproject.dto.EmployeeRelationshipDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "EmployeeRelationshipMapper",
        classes = @ConstructorResult(
                targetClass = EmployeeRelationshipDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "date_of_birth", type = LocalDate.class),
                        @ColumnResult(name = "identification_number", type = String.class),
                        @ColumnResult(name = "employee_relationship", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "employee_id", type = Integer.class)
                }
        )
)
@Entity
public class EmployeeRelationshipMapper {
    @Id
    private Integer id;
}
