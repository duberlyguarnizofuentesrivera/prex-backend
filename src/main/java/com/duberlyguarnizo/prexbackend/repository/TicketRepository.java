package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.TicketPaymentStatus;
import com.duberlyguarnizo.prexbackend.enums.TicketStatus;
import com.duberlyguarnizo.prexbackend.model.Ticket;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTicketCode(String ticketCode);

    List<Ticket> findByTicketClient_ClientId(Long clientId);//NOSONAR

    List<Ticket> findByTicketClient_ClientIdNumber(String clientIdNumber);//NOSONAR

    List<Ticket> findByTicketSystemUser_SystemUserId(Long systemUserId, PageRequest pageRequest);//NOSONAR

    List<Ticket> findByTicketStatus(TicketStatus ticketStatus, PageRequest pageRequest);

    List<Ticket> findByTicketPaymentStatus(TicketPaymentStatus ticketPaymentStatus, PageRequest pageRequest);

    List<Ticket> findByTicketModificationDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
