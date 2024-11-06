package com.myproject.service.impl;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.OctResourceNotFoundException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.dto.RoleDTO;
import com.myproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.myproject.constant.StoredProcedureConst.Role.GET_ROLE_BY_ID;
import static com.myproject.constant.StoredProcedureConst.Role.GET_ROLE_BY_NAME;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final EntityManager entityManager;
    @Override
    public RoleDTO getRoleById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ROLE_BY_ID, "RoleMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        try {
            return (RoleDTO) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctResourceNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(MessageErrorConst.ROLE_NOT_FOUND));
        }

    }

    @Override
    public RoleDTO getRoleByName(String roleName) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ROLE_BY_NAME, "RoleMapper")
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .setParameter("p_name", roleName);
        try {
            return (RoleDTO) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctResourceNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(MessageErrorConst.ROLE_NOT_FOUND));
        }
    }
}
