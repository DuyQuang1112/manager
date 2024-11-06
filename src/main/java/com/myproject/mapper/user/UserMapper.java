package com.myproject.mapper.user;

import com.myproject.dto.user.UserDTO;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "UserMapper",
        classes = @ConstructorResult(
                targetClass = UserDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "password", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "role_id", type = Integer.class)
                }
        )
)
@Entity
public class UserMapper {
    @Id
    private Integer id;
}
