package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.UserRole;
import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    List<SystemUser> findBySystemUserUsername(String username);

    Page<SystemUser> findBySystemUserStatus(UserStatus systemUserStatus, PageRequest pageRequest);

    Page<SystemUser> findBySystemUserRole(UserRole systemUserRole, PageRequest pageRequest);
}
