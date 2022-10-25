package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.repository.TicketRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
