package com.duberlyguarnizo.prexbackend.model;

import com.duberlyguarnizo.prexbackend.enums.ShipmentProblem;
import com.duberlyguarnizo.prexbackend.enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    private String shipmentCode;
    @Min(1)
    private int shipmentNumberOfPackages;
    private LocalDateTime shipmentReceptionDate;
    private LocalDateTime shipmentOnRouteDate;
    private LocalDateTime shipmentOnReturnDate;
    private LocalDateTime shipmentReturnDate;
    private LocalDateTime shipmentDeliveryDate;
    @ToString.Exclude
    @ManyToOne
    private Client shipmentClient;
    @ToString.Exclude
    @ManyToOne
    private Address shipmentAddress;
    @ToString.Exclude
    @ManyToOne
    private Receiver shipmentReceiver;
    @ToString.Exclude
    @ManyToOne
    private SystemUser shipmentDeliveryTransporter;
    @ToString.Exclude
    @ManyToOne
    private SystemUser shipmentPickUpTransporter;
    @ToString.Exclude
    @ManyToOne
    private SystemUser shipmentReceiverUser;
    @Positive
    private double shipmentCost;
    @ElementCollection
    private @NotNull Set<String> shipmentPhotoUrls = new HashSet<>();
    private String shipmentObservations;
    @Enumerated(EnumType.STRING)
    private ShipmentProblem shipmentProblems;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
    @CreationTimestamp
    private LocalDateTime shipmentCreationDate;
    @UpdateTimestamp
    private LocalDateTime shipmentModificationDate;
}
