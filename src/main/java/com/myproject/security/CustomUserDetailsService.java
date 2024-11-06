package com.myproject.security;

import com.myproject.dto.user.UserDTO;
import com.myproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.myproject.constant.StoredProcedureConst.User;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EntityManager entityManager;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(User.GET_BY_USERNAME, "UserMapper")
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .setParameter("p_username", username);
        UserDTO userDTO = (UserDTO) query.getSingleResult();
        System.out.println(roleService.getRoleById(userDTO.getRoleId()).getName());
        return new CustomUserDetails(userDTO, roleService.getRoleById(userDTO.getRoleId()).getName());
    }
}
