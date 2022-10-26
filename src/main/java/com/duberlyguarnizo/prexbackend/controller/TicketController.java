package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.enums.TicketPaymentStatus;
import com.duberlyguarnizo.prexbackend.enums.TicketStatus;
import com.duberlyguarnizo.prexbackend.model.Ticket;
import com.duberlyguarnizo.prexbackend.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket result = ticketRepository.save(ticket);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        Ticket result = ticketRepository.save(ticket);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
        Ticket result = ticketRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Ticket>> getAllTicket(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Ticket> result = ticketRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Custom methods
    //TODO:rename two word paths to use hyphen
    @GetMapping("/by-client/{id}")
    public ResponseEntity<List<Ticket>> getTicketByClientId(@PathVariable("id") Long id) {
        List<Ticket> result = ticketRepository.findByTicketClient_ClientId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-client-dni/{dni}")
    public ResponseEntity<List<Ticket>> getTicketByClientId(@PathVariable("dni") String dni) {
        List<Ticket> result = ticketRepository.findByTicketClient_ClientIdNumber(dni);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<Ticket> getTicketByCode(@PathVariable("code") String code) {
        List<Ticket> result = ticketRepository.findByTicketCode(code);
        //there should be only one ticket with the same code
        return new ResponseEntity<>(result.get(0), HttpStatus.OK);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<Ticket>> getTicketsByDate(@PathVariable("date") LocalDate date) {
        List<Ticket> result = ticketRepository.findByTicketModificationDateBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-payment/{payment}")
    public ResponseEntity<List<Ticket>> getTicketsByPaymentStatus(@PathVariable("payment") String paymentStatus, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            TicketPaymentStatus ticketPaymentStatus = TicketPaymentStatus.valueOf(paymentStatus);
            List<Ticket> result = ticketRepository.findByTicketPaymentStatus(ticketPaymentStatus, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable("status") String status, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        try {
            TicketStatus ticketStatus = TicketStatus.valueOf(status);
            List<Ticket> result = ticketRepository.findByTicketStatus(ticketStatus, PageRequest.of(page, size));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-user-id/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable("id") Long id, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        List<Ticket> result = ticketRepository.findByTicketSystemUser_SystemUserId(id, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
