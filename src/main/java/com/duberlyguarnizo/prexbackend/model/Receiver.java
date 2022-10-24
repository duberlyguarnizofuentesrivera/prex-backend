package com.duberlyguarnizo.prexbackend.model;

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
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiverId;
    private boolean receiverIsCompany;
    @NotBlank
    private String receiverNames;
    private String receiverIdNumber;
    private String receiverPhone;
    @Email
    private String receiverEmail;
    private String receiverContactNames;
    @ToString.Exclude
    @OneToMany
    private Set<Address> receiverAddresses;
    @CreationTimestamp
    private LocalDateTime receiverCreationDate;
    @UpdateTimestamp
    private LocalDateTime receiverModificationDate;
}
