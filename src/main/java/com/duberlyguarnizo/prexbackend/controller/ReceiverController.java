package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.model.Receiver;
import com.duberlyguarnizo.prexbackend.repository.ReceiverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {
    private final ReceiverRepository receiverRepository;

    public ReceiverController(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<Receiver> createReceiver(@RequestBody Receiver receiver) {
        Receiver result = receiverRepository.save(receiver);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Receiver> updateReceiver(@RequestBody Receiver receiver) {
        Receiver result = receiverRepository.save(receiver);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReceiver(@PathVariable("id") Long id) {
        receiverRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receiver> getReceiver(@PathVariable("id") Long id) {
        Receiver result = receiverRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Receiver>> getAllReceivers(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Receiver> result = receiverRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Custom methods
    @GetMapping("/name/{name}")
    public ResponseEntity<Page<Receiver>> getReceiversByName(@PathVariable("name") String name, @RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Receiver> result = receiverRepository.findByReceiverNames(name, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Receiver> getReceiverByDni(@PathVariable("dni") String dni) {
        List<Receiver> result = receiverRepository.findByReceiverIdNumber(dni);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.get(0), HttpStatus.OK);
    }

    @GetMapping("/is_company")
    public ResponseEntity<List<Receiver>> getReceiversThatAreCompanies() {
        List<Receiver> result = receiverRepository.findByReceiverIsCompany(true);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/is_not_company")
    public ResponseEntity<List<Receiver>> getReceiversThatAreNotCompanies() {
        List<Receiver> result = receiverRepository.findByReceiverIsCompany(false);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Receiver>> getReceiversByDate(@PathVariable("date") LocalDate date) {
        List<Receiver> result = receiverRepository.findByReceiverModificationDateBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
