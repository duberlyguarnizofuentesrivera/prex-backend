package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.model.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    List<Receiver> findByReceiverNames(String receiverNames);

    List<Receiver> findByReceiverIdNumber(String receiverIdNumber);

    List<Receiver> findByReceiverIsCompany(boolean isCompany);

    List<Receiver> findByReceiverModificationDate(LocalDateTime dateTime);
}
