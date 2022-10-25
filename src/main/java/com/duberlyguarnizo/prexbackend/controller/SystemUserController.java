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
        systemUser.setSystemUserUsername("noemi");

        systemUser.setSystemUserPassword(
                jwtSecurityConfig.passwordEncoder().encode("noemi")
        );
        systemUser.setSystemUserRole(UserRole.ADMIN);
        systemUser.setSystemUserCreationDate(LocalDateTime.now());
        systemUser.setSystemUserModificationDate(LocalDateTime.now());
        systemUser.setSystemUserIdNumber("12345678");
        systemUser.setSystemUserEmail("noemi@gmail.com");
        systemUser.setSystemUserNames("Noemi Viviana Timana Becerra");
        systemUser.setSystemUserPhone("123456789");
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
    public ResponseEntity<SystemUser> deleteSystemUser(@PathVariable Long id) {
        systemUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemUser> getSystemUser(@PathVariable Long id) {
        SystemUser result = systemUserRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SystemUser>> getAllSystemUser(@RequestParam int page, @RequestParam int size) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 15;
        }
        Page<SystemUser> result = systemUserRepository.findAll(PageRequest.of(page, size));
        result.getTotalPages();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Page<SystemUser>> getAllSystemUsersByRole(@RequestParam int page, @RequestParam int size, @RequestParam UserRole role) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 15;
        }
        Page<SystemUser> result = systemUserRepository.findBySystemUserRole(role, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Page<SystemUser>> getAllSystemUsersByUserStatus(@RequestParam int page, @RequestParam int size, @RequestParam UserStatus status) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 15;
        }
        Page<SystemUser> result = systemUserRepository.findBySystemUserStatus(status, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<SystemUser> getSystemUsersByUserName(String username) {
        SystemUser result = systemUserRepository.findBySystemUserUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
