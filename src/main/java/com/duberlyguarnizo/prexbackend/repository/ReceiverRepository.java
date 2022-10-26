package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.model.Receiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    Page<Receiver> findByReceiverNames(String receiverNames, PageRequest page);

    List<Receiver> findByReceiverIdNumber(String receiverIdNumber);

    List<Receiver> findByReceiverIsCompany(boolean isCompany);

    List<Receiver> findByReceiverModificationDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
