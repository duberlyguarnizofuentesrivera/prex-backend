package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.ShipmentProblem;
import com.duberlyguarnizo.prexbackend.enums.ShipmentStatus;
import com.duberlyguarnizo.prexbackend.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByShipmentCode(String shipmentCode);

    List<Shipment> findByShipmentReceptionDateNotNull();

    List<Shipment> findByShipmentOnRouteDateNotNull();

    List<Shipment> findByShipmentOnReturnDateNotNull();

    List<Shipment> findByShipmentReturnDateNotNull();

    List<Shipment> findByShipmentDeliveryDateNotNull();

    List<Shipment> findByShipmentClient_ClientId(Long clientId);

    List<Shipment> findByShipmentReceiver_ReceiverId(Long receiverId);

    List<Shipment> findByShipmentDeliveryTransporter_SystemUserId(Long systemUserId);

    List<Shipment> findByShipmentPickUpTransporter_SystemUserId(Long systemUserId);

    List<Shipment> findByShipmentReceiverUser_SystemUserId(Long systemUserId);

    List<Shipment> findByShipmentModificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Shipment> findByShipmentProblems(ShipmentProblem shipmentProblem);

    List<Shipment> findByShipmentStatus(ShipmentStatus shipmentStatus);
}
