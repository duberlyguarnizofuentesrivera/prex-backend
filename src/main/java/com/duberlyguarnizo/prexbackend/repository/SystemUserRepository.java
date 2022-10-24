package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.UserRole;
import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    List<SystemUser> findBySystemUserStatus(UserStatus systemUserStatus);

    List<SystemUser> findBySystemUserRole(UserRole systemUserRole);
}
