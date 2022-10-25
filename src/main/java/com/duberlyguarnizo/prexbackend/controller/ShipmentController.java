package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.repository.ShipmentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;

    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
}
