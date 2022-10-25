package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.repository.ReceiverRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {
    private final ReceiverRepository receiverRepository;

    public ReceiverController(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }
}
