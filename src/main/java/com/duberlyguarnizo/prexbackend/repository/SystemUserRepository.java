package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.UserRole;
import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    SystemUser findBySystemUserUsername(String username);

    Page<SystemUser> findBySystemUserStatus(UserStatus systemUserStatus, PageRequest pageRequest);

    Page<SystemUser> findBySystemUserRole(UserRole systemUserRole, PageRequest pageRequest);
}
