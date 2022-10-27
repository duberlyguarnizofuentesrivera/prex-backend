package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.enums.ShipmentProblem;
import com.duberlyguarnizo.prexbackend.enums.ShipmentStatus;
import com.duberlyguarnizo.prexbackend.model.Shipment;
import com.duberlyguarnizo.prexbackend.repository.ShipmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;

    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment result = shipmentRepository.save(shipment);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Shipment> updateShipment(@RequestBody Shipment shipment) {
        Shipment result = shipmentRepository.save(shipment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable("id") Long id) {
        shipmentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipment(@PathVariable("id") Long id) {
        Shipment result = shipmentRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Shipment>> getAllShipment(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Shipment> result = shipmentRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Custom methods
    @GetMapping("/by-client-id/{id}")
    public ResponseEntity<List<Shipment>> getShipmentsByClientId(@PathVariable("id") Long id) {
        List<Shipment> result = shipmentRepository.findByShipmentClient_ClientId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-transporter-id/{id}")
    public ResponseEntity<List<Shipment>> getShipmentsByTransporterId(@PathVariable("id") Long id, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentDeliveryTransporter_SystemUserId(id, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-pick-up-transporter-id/{id}")
    public ResponseEntity<List<Shipment>> getShipmentsByPickUpTransporterId(@PathVariable("id") Long id, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentPickUpTransporter_SystemUserId(id, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-receiving-user-id/{id}")
    public ResponseEntity<List<Shipment>> getShipmentsByReceivingUserId(@PathVariable("id") Long id, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentReceiverUser_SystemUserId(id, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-receiver-id/{id}")
    public ResponseEntity<List<Shipment>> getShipmentsByReceiverId(@PathVariable("id") Long id) {
        List<Shipment> result = shipmentRepository.findByShipmentReceiver_ReceiverId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-ticket-code/{code}")
    public ResponseEntity<List<Shipment>> getShipmentsByTicketCode(@PathVariable("code") String code) {
        List<Shipment> result = shipmentRepository.findByShipmentCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<Shipment>> getShipmentByDate(@PathVariable("date") LocalDate date, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentModificationDateBetween(date.atStartOfDay(), date.atTime(13, 59, 59), PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-delivered")
    public ResponseEntity<List<Shipment>> getShipmentsDeliveredDateSet(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentDeliveryDateNotNull(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-on-return")
    public ResponseEntity<List<Shipment>> getShipmentsOnReturnDateSet(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentOnReturnDateNotNull(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-on-route")
    public ResponseEntity<List<Shipment>> getShipmentsOnRouteDateSet(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentOnRouteDateNotNull(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-returned")
    public ResponseEntity<List<Shipment>> getShipmentsReturnDateSet(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentReturnDateNotNull(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-received")
    public ResponseEntity<List<Shipment>> getShipmentsReceptionDateSet(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Shipment> result = shipmentRepository.findByShipmentReceptionDateNotNull(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Shipment>> getShipmentsByStatus(@PathVariable("status") String status, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(status);
            List<Shipment> result = shipmentRepository.findByShipmentStatus(shipmentStatus, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-problems/{problem}")
    public ResponseEntity<List<Shipment>> getShipmentsByProblem(@PathVariable("problem") String problem, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            ShipmentProblem shipmentProblem = ShipmentProblem.valueOf(problem);
            List<Shipment> result = shipmentRepository.findByShipmentProblems(shipmentProblem, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
