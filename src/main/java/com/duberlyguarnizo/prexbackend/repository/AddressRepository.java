package com.duberlyguarnizo.prexbackend.repository;

import com.duberlyguarnizo.prexbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByAddressRegion(String addressRegion);

    List<Address> findByAddressRegionAndAddressProvince(String addressRegion, String addressProvince);

    List<Address> findByAddressRegionAndAddressProvinceAndAddressDistrict(String addressRegion, String addressProvince, String addressDistrict);

    List<Address> findByAddressModificationDate(LocalDateTime dateTime);
}

