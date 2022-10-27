package com.duberlyguarnizo.prexbackend.model;

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
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @NotBlank
    @Column(unique = true)
    private String clientIdNumber;
    private boolean clientIsCompany;
    @NotBlank
    private String clientNames;
    private String clientContactNames;
    private String clientPhone;
    @Email
    private String clientEmail;
    @Enumerated(EnumType.STRING)
    private UserStatus clientStatus;
    @ToString.Exclude
    @OneToMany
    private Set<Address> clientPickUpAddresses = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime clientCreationDate;
    @UpdateTimestamp
    private LocalDateTime clientModificationDate;
}