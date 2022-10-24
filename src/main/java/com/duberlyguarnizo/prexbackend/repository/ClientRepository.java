package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByClientNames(String clientNames);
    List<Client> findByClientIdNumber(String clientIdNumber);
    List<Client> findByClientStatus(UserStatus clientStatus);
    List<Client> findByClientModificationDate(LocalDateTime dateTime);
}
