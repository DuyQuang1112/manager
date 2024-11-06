package com.myproject.service.impl;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.OctIllegalRequestException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst.User;
import com.myproject.dto.JwtResponse;
import com.myproject.dto.user.UserDTO;
import com.myproject.dto.user.UserLogin;
import com.myproject.security.CustomUserDetails;
import com.myproject.security.JwtTokenProvider;
import com.myproject.service.UserService;
import com.myproject.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserValidation userValidation;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userValidation.validateUserRegister(userDTO);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(User.CREATE, "UserMapper")
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_role_id", Integer.class, ParameterMode.IN)
                .setParameter("p_username", userDTO.getUsername())
                .setParameter("p_password", userDTO.getPassword())
                .setParameter("p_name", userDTO.getName())
                .setParameter("p_role_id", userDTO.getRoleId());


        return (UserDTO) query.getSingleResult();
    }

    @Override
    public JwtResponse logIn(UserLogin userLogin) {
        try{
            userValidation.validateUserLogin(userLogin.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.getUsername(),
                            userLogin.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = jwtTokenProvider.generateToken(userDetails);
            return new JwtResponse(jwt);
        } catch (BadCredentialsException exception) {
            throw new OctIllegalRequestException(ErrorMessages.LOGIN_FAILED, new ApiMessageError(MessageErrorConst.PASSWORD_INVALID));
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDTO> getAllLeader() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(User.GET_ALL_LEADER, "UserMapper");
        return (List<UserDTO>) query.getResultList();
    }

    @Override
    public UserDTO getByUsername(String username) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(User.GET_BY_USERNAME, "UserMapper")
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .setParameter("p_username", username);
        return (UserDTO) query.getSingleResult();
    }


}
