package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;

@Repository
@Transactional
public interface ConnectionRepository extends JpaRepository<Connection, Long>{
  List<Connection> findByFromPartyIdAndConnectionType(Long fromPartyId, ConnectionType connectionType);
  Connection findByFromPartyIdAndToPartyId(Long fromPartyId, Long toPartyId);
//  
//  @Query("select pg "
//      + "from Connection c "
//      + "join Party p on c.fromPartyId=p.id "
//      + "join PrivateGroup pg on p.id=pg.id "
//      + "where c.fromPartyId=?1 and "
//      + "c.connectionType=?2 and "
//      + "pg.privateGroupName=?3")
  @Query("select p from "
      + "Connection c inner join c.toParty p "
      + "where c.fromPartyId=?1 and "
      + "c.connectionType=?2 and "
      + "p.privateGroupName=?3")
  PrivateGroup findByFromPartyIdAndConnectionTypeAndPrivateGroupName(Long fromPartyId, ConnectionType connectionType, String privateGroupName);
  
  @Modifying
  @Query("delete from Connection c "
      + "where c.fromPartyId=?1 "
      + "and c.toPartyId=?2")
  void deleteById(Long fromId, Long toId);
  
  @Query("select p from "
      + "Connection c inner join c.toParty p "
      + "where c.fromPartyId=?1 and "
      + "c.connectionType=?2 "
      + "order by p.firstName asc")
  List<Person> findPersonByFromPartyIdAndConnectionTypeOrderByFirstName(Long fromPartyId, ConnectionType connectionType);
  
  @Query("select p from "
      + "PrivateGroup p inner join p.fromParties f inner join p.toParties t "
      + "where f.fromPartyId=?1 and "
      + "t.toPartyId=?2")
  PrivateGroup findPrivateGroupByGroupOwnerIdAndGroupMemberId(Long groupOwnerId, Long groupMemberId);
}
