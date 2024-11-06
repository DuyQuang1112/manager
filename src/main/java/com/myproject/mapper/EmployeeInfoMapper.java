package com.myproject.mapper;

import com.myproject.dto.EmployeeInfoDTO;

import javax.persistence.*;
import java.time.LocalDate;


@SqlResultSetMapping(
        name = "EmployeeInfoMapper",
        classes =  { @ConstructorResult(
                targetClass = EmployeeInfoDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "code", type = String.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "date_of_birth", type = LocalDate.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "team", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "identification_number", type = String.class),
                        @ColumnResult(name = "phone_number", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
        }
)
@Entity
public class EmployeeInfoMapper {
    @Id
    private Integer id;
}

