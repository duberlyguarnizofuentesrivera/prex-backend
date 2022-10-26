package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.enums.UserStatus;
import com.duberlyguarnizo.prexbackend.model.Client;
import com.duberlyguarnizo.prexbackend.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client result = clientRepository.save(client);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client result = clientRepository.save(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Client result = clientRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Client>> getAllClient(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Client> result = clientRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Custom methods
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Client> getClientByDni(@PathVariable("dni") String dni) {
        List<Client> result = clientRepository.findByClientIdNumber(dni);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //there should be only one result
        return new ResponseEntity<>(result.get(0), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable("name") String name) {
        List<Client> result = clientRepository.findByClientNames(name);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Client>> getClientByStatus(@PathVariable("status") String status) {
        try {
            UserStatus userStatus = UserStatus.valueOf(status);
            List<Client> result = clientRepository.findByClientStatus(userStatus);
            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Client>> getClientByDate(@PathVariable("date") LocalDate date) {
        List<Client> result = clientRepository.findByClientModificationDateBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
