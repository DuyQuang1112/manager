package com.myproject.mapper;

import com.myproject.dto.RoleDTO;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "RoleMapper",
        classes = @ConstructorResult(
                targetClass = RoleDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class)
                }
        )
)
@Entity
public class RoleMapper {
    @Id
    private Integer id;
}
