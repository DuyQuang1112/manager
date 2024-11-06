package com.myproject.mapper;

import com.myproject.dto.EmployeeCertificateDTO;

import javax.persistence.*;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "EmployeeCertificateMapper",
        classes = @ConstructorResult(
                targetClass = EmployeeCertificateDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "date_of_issue", type = LocalDate.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "major", type = String.class),
                        @ColumnResult(name = "employee_id", type = Integer.class),
                }
        )
)
@Entity
public class EmployeeCertificateMapper {
    @Id
    private Integer id;
}
