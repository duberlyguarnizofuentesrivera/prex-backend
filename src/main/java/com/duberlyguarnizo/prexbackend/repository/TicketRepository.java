package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.TicketPaymentStatus;
import com.duberlyguarnizo.prexbackend.enums.TicketStatus;
import com.duberlyguarnizo.prexbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTicketCode(String ticketCode);

    List<Ticket> findByTicketClient_ClientId(Long clientId);

    List<Ticket> findByTicketClient_ClientIdNumber(String clientIdNumber);

    List<Ticket> findByTicketSystemUser_SystemUserId(Long systemUserId);

    List<Ticket> findByTicketStatus(TicketStatus ticketStatus);

    List<Ticket> findByTicketPaymentStatus(TicketPaymentStatus ticketPaymentStatus);

    List<Ticket> findByTicketModificationDate(LocalDateTime dateTime);
}
