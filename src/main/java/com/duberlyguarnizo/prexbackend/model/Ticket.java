package com.duberlyguarnizo.prexbackend.model;

import com.duberlyguarnizo.prexbackend.enums.TicketPaymentStatus;
import com.duberlyguarnizo.prexbackend.enums.TicketStatus;
import com.duberlyguarnizo.prexbackend.generators.TicketCodeGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @GeneratorType(type = TicketCodeGenerator.class, when = GenerationTime.INSERT)
    private String ticketCode;
    @OneToMany
    @ToString.Exclude
    private List<Shipment> shipments = new ArrayList<>();
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
