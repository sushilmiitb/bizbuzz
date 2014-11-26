package com.bizbuzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bizbuzz.model.Party;
import com.bizbuzz.model.RegisterDevice;


public interface RegisterDeviceRepository extends JpaRepository<RegisterDevice,Long> {
  /*
  @Query("select rd from "
      + "RegisterDevice rd inner join rd.party p "
      + "where p.id=?1" )           */
    RegisterDevice findByPartyId(Long partyId);
}
