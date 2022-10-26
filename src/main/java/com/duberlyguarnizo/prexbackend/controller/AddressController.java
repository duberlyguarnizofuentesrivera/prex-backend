package com.duberlyguarnizo.prexbackend.controller;

import com.duberlyguarnizo.prexbackend.model.Address;
import com.duberlyguarnizo.prexbackend.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //CRUD methods
    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address result = addressRepository.save(address);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        Address result = addressRepository.save(address);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
        Address result = addressRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Address>> getAllAddress(@RequestParam(defaultValue = "10") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Page<Address> result = addressRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Custom methods
    //TODO: check if path "date" is parsed correctly to LocalDate
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Address>> getAddressByDate(@PathVariable("date") LocalDate date) {
        List<Address> result = addressRepository.
                findByAddressModificationDateBetween(date.atStartOfDay(),
                        date.atTime(23, 59, 59));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/location/{region}")
    public ResponseEntity<List<Address>> getAddressByRegion(@PathVariable("region") String region) {
        List<Address> result = addressRepository.findByAddressRegion(region);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/location/{region}/{province}")
    public ResponseEntity<List<Address>> getAddressByProvince(
            @PathVariable("region") String region,
            @PathVariable("province") String province) {
        List<Address> result = addressRepository.findByAddressRegionAndAddressProvince(region, province);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/location/{region}/{province}/{district}")
    public ResponseEntity<List<Address>> getAddressByDistrict(
            @PathVariable("region") String region,
            @PathVariable("province") String province,
            @PathVariable("district") String district) {
        List<Address> result = addressRepository.
                findByAddressRegionAndAddressProvinceAndAddressDistrict(
                        region,
                        province,
                        district);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
