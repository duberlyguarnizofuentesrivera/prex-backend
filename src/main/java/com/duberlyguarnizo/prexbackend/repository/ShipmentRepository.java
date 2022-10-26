package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.enums.ShipmentProblem;
import com.duberlyguarnizo.prexbackend.enums.ShipmentStatus;
import com.duberlyguarnizo.prexbackend.model.Shipment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByShipmentCode(String shipmentCode);

    List<Shipment> findByShipmentReceptionDateNotNull(PageRequest pageRequest);

    List<Shipment> findByShipmentOnRouteDateNotNull(PageRequest pageRequest);

    List<Shipment> findByShipmentOnReturnDateNotNull(PageRequest pageRequest);

    List<Shipment> findByShipmentReturnDateNotNull(PageRequest pageRequest);

    List<Shipment> findByShipmentDeliveryDateNotNull(PageRequest pageRequest);

    List<Shipment> findByShipmentClient_ClientId(Long clientId);//NOSONAR

    List<Shipment> findByShipmentReceiver_ReceiverId(Long receiverId);//NOSONAR

    List<Shipment> findByShipmentDeliveryTransporter_SystemUserId(Long systemUserId, PageRequest pageRequest);//NOSONAR

    List<Shipment> findByShipmentPickUpTransporter_SystemUserId(Long systemUserId, PageRequest pageRequest);//NOSONAR

    List<Shipment> findByShipmentReceiverUser_SystemUserId(Long systemUserId, PageRequest pageRequest);//NOSONAR

    List<Shipment> findByShipmentModificationDateBetween(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest);

    List<Shipment> findByShipmentProblems(ShipmentProblem shipmentProblem, PageRequest pageRequest);

    List<Shipment> findByShipmentStatus(ShipmentStatus shipmentStatus, PageRequest pageRequest);
}
