package com.duberlyguarnizo.prexbackend.model;

import com.duberlyguarnizo.prexbackend.enums.UserRole;
import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemUserId;
    @NotBlank
    private String systemUserNames;
    @NotBlank
    @Column(unique = true)
    private String systemUserIdNumber;
    private String systemUserPhone;
    private String systemUserPhone2;
    @Email
    private String systemUserEmail;
    @Enumerated(EnumType.STRING)
    private UserStatus systemUserStatus;
    @Enumerated(EnumType.STRING)
    private UserRole systemUserRole;
    @NotBlank
    private String systemUserUsername;
    @NotBlank
    private String systemUserPassword;
    @CreationTimestamp
    private LocalDateTime systemUserCreationDate;
    @UpdateTimestamp
    private LocalDateTime systemUserModificationDate;
}
