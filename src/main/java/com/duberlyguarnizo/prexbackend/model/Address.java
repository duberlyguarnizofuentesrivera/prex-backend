package com.duberlyguarnizo.prexbackend.model;

import jakarta.persistence.*;
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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addressRegion;
    private String addressProvince;
    private String addressDistrict;
    @NotBlank
    private String addressLine;
    private String addressObservations;
    @CreationTimestamp
    private LocalDateTime addressCreationDate;
    @UpdateTimestamp
    private LocalDateTime addressModificationDate;
}
