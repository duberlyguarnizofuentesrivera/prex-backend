package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.enums.UserRole;
import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.SystemUser;
import com.duberlyguarnizo.prexbackend.repository.SystemUserRepository;
import com.duberlyguarnizo.prexbackend.security.JwtSecurityConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class SystemUserController {
    private final SystemUserRepository systemUserRepository;
    private final JwtSecurityConfig jwtSecurityConfig;

    public SystemUserController(SystemUserRepository systemUserRepository, JwtSecurityConfig jwtSecurityConfig) {
        this.systemUserRepository = systemUserRepository;
        this.jwtSecurityConfig = jwtSecurityConfig;
    }

    //TODO: delete this method after testing
    @GetMapping("/create-sample-user")
    public ResponseEntity<SystemUser> createSampleUser() {
        SystemUser systemUser = new SystemUser();
        systemUser.setSystemUserUsername("bruno");

        systemUser.setSystemUserPassword(
                jwtSecurityConfig.passwordEncoder().encode("bruno")
        );
        systemUser.setSystemUserRole(UserRole.USER);
        systemUser.setSystemUserCreationDate(LocalDateTime.now());
        systemUser.setSystemUserModificationDate(LocalDateTime.now());
        systemUser.setSystemUserIdNumber("74525845");
        systemUser.setSystemUserEmail("brunito_mas_na@google.com");
        systemUser.setSystemUserNames("Bruno Sebastian Guarnizo Timana");
        systemUser.setSystemUserPhone("954687552");
        systemUser.setSystemUserStatus(UserStatus.ACTIVE);
        systemUserRepository.save(systemUser);
        systemUserRepository.save(systemUser);
        return new ResponseEntity<>(systemUser, HttpStatus.OK);
    }

    //get current user
    @GetMapping("/logged-user")
    public Principal loggedUser(Principal user) {
        return user;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<SystemUser> createSystemUser(@RequestBody SystemUser systemUser) {
        SystemUser result = systemUserRepository.save(systemUser);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SystemUser> updateSystemUser(@RequestBody SystemUser systemUser) {
        SystemUser result = systemUserRepository.save(systemUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SystemUser> deleteSystemUser(@PathVariable("id") Long id) {
        systemUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemUser> getSystemUser(@PathVariable("id") Long id) {
        SystemUser result = systemUserRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SystemUser>> getAllSystemUser(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<SystemUser> result = systemUserRepository.findAll(PageRequest.of(page, size));
        result.getTotalPages();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<Page<SystemUser>> getAllSystemUsersByRole(@PathVariable("role") String role, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            UserRole userRole = UserRole.valueOf(role);
            Page<SystemUser> result = systemUserRepository.findBySystemUserRole(userRole, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<SystemUser>> getAllSystemUsersByUserStatus(@PathVariable("status") String status, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            UserStatus userStatus = UserStatus.valueOf(status);
            Page<SystemUser> result = systemUserRepository.findBySystemUserStatus(userStatus, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<SystemUser> getSystemUsersByUserName(@PathVariable("username") String username) {
        SystemUser result = systemUserRepository.findBySystemUserUsername(username);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
