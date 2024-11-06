package com.myproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class StoredProcedureConst {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EmployeeInfo {
        public static final String GET_BY_ID = "employee_get_by_id";
        public static final String GET_ALL = "employee_get_all";
        public static final String CREATE = "employee_create";
        public static final String UPDATE = "employee_update_by_id";
        public static final String DELETE = "employee_delete_by_id";
        public static final String EXISTS_BY_ID = "employee_exist_by_id";
        public static final String CHECK_DUPLICATE_EMAIL = "employee_check_duplicate_email";
        public static final String CHECK_DUPLICATE_PHONE_NUMBER = "employee_check_duplicate_phone_number";
        public static final String CHECK_DUPLICATE_IDENTIFICATION_NUMBER = "employee_check_duplicate_identification_number";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EmployeeCertificate {
        public static final String GET_BY_ID = "certificate_get_by_id";
        public static final String GET_BY_EMPLOYEE_ID = "certificate_get_by_employee_id";
        public static final String CREATE = "certificate_create";
        public static final String UPDATE = "certificate_update_by_id";
        public static final String DELETE = "certificate_delete_by_id";
        public static final String EXIST_BY_ID = "certificate_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EmployeeRelationship {
        public static final String GET_BY_ID = "relationship_get_by_id";
        public static final String GET_BY_EMPLOYEE_ID = "relationship_get_by_employee_id";
        public static final String CREATE = "relationship_create";
        public static final String UPDATE = "relationship_update_by_id";
        public static final String DELETE = "relationship_delete_by_id";
        public static final String EXISTS_BY_ID = "relationship_exist_by_id";
        public static final String CHECK_DUPLICATE_IDENTIFICATION_NUMBER = "relationship_check_duplicate_identification_number";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String GET_ALL_LEADER = "user_get_all_leader";
        public static final String CREATE = "user_create";
        public static final String GET_BY_USERNAME = "user_get_by_username";
        public static final String EXISTS_BY_USERNAME = "user_exist_by_username";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Role {
        public static final String GET_ROLE_BY_ID = "role_get_by_id";
        public static final String GET_ROLE_BY_NAME = "role_get_by_name";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RegistrationForm {
        public static final String GET_BY_ID = "registration_get_by_id";
        public static final String GET_ALL = "registration_get_all";
        public static final String GET_BY_LEADER_ID = "registration_get_by_leader";
        public static final String CREATE = "registration_create";
        public static final String UPDATE_BY_MANAGER = "registration_update_by_manager";
        public static final String UPDATE_BY_LEADER = "registration_update_by_leader";
        public static final String SUBMIT = "registration_submit";
        public static final String DELETE_BY_ID = "registration_delete_by_id";
        public static final String EXIST_BY_ID = "registration_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProfileEnd {
        public static final String GET_BY_ID = "profile_end_get_by_id";
        public static final String GET_ALL = "profile_end_get_all";
        public static final String GET_BY_LEADER_ID = "profile_end_get_by_leader";
        public static final String CREATE = "profile_end_create";
        public static final String UPDATE_BY_MANAGER = "profile_end_update_by_manager";
        public static final String UPDATE_BY_LEADER = "profile_end_update_by_leader";
        public static final String SUBMIT = "profile_end_submit";
        public static final String DELETE_BY_ID = "profile_end_delete_by_id";
        public static final String EXIST_BY_ID = "profile_end_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SalaryIncrements {
        public static final String GET_BY_ID = "salary_increments_get_by_id";
        public static final String GET_ALL = "salary_increments_get_all";
        public static final String GET_BY_LEADER_ID = "salary_increments_get_by_leader";
        public static final String CREATE = "salary_increments_create";
        public static final String UPDATE_BY_MANAGER = "salary_increments_update_by_manager";
        public static final String UPDATE_BY_LEADER = "salary_increments_update_by_leader";
        public static final String SUBMIT = "salary_increments_submit";
        public static final String DELETE_BY_ID = "salary_increments_delete_by_id";
        public static final String EXIST_BY_ID = "salary_increments_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PromoteForm {
        public static final String GET_BY_ID = "promote_get_by_id";
        public static final String GET_ALL = "promote_get_all";
        public static final String GET_BY_LEADER_ID = "promote_get_by_leader";
        public static final String CREATE = "promote_create";
        public static final String UPDATE_BY_MANAGER = "promote_update_by_manager";
        public static final String UPDATE_BY_LEADER = "promote_update_by_leader";
        public static final String SUBMIT = "promote_submit";
        public static final String DELETE_BY_ID = "promote_delete_by_id";
        public static final String EXIST_BY_ID = "promote_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProposeForm {
        public static final String GET_BY_ID = "propose_get_by_id";
        public static final String GET_ALL = "propose_get_all";
        public static final String GET_BY_LEADER_ID = "propose_get_by_leader";
        public static final String CREATE = "propose_create";
        public static final String UPDATE_BY_MANAGER = "propose_update_by_manager";
        public static final String UPDATE_BY_LEADER = "propose_update_by_leader";
        public static final String SUBMIT = "propose_submit";
        public static final String DELETE_BY_ID = "propose_delete_by_id";
        public static final String EXIST_BY_ID = "propose_exist_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Category {
        public static final String GET_BY_ID = "category_get_by_id";
        public static final String CREATE = "category_create";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class staff {
        public static final String GET_BY_ID = "staff_get_by_id";
        public static final String CREATE = "staff_create";
    }
}
