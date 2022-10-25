package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.repository.ClientRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
