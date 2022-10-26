package com.duberlyguarnizo.prexbackend.model;

import com.duberlyguarnizo.prexbackend.enums.TicketPaymentStatus;
import com.duberlyguarnizo.prexbackend.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @NotBlank
    @Column(unique = true)
    private String ticketCode;
    @OneToMany
    private List<Shipment> shipments;
    private double ticketTotalCost;
    @ToString.Exclude
    @ManyToOne
    private Client ticketClient;
    @ToString.Exclude
    @ManyToOne
    private SystemUser ticketSystemUser;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    @Enumerated(EnumType.STRING)
    private TicketPaymentStatus ticketPaymentStatus;
    @CreationTimestamp
    private LocalDateTime ticketCreationDate;
    @UpdateTimestamp
    private LocalDateTime ticketModificationDate;
}
