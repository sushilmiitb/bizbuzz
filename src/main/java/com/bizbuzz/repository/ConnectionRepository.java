package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Party;
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
      + "p.id=?3")
  PrivateGroup findPrivateGroupByFromPartyIdAndConnectionTypeAndId(Long fromPartyId, ConnectionType connectionType, Long id);
  
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
      + "Connection c inner join c.toParty p "
      + "where c.fromPartyId=?1 and "
      + "p.id=?2")
  Person findPersonByFromPartyIdAndId(Long fromPartyId, Long id);
  
  
  /**
   * This function returns Private Group which connects group owner with group member
   * @param fromPartyId: group owner id
   * @param toPartyId: group member id
   * @return
   */
  @Query("select p from "
      + "PrivateGroup p inner join p.fromParties f inner join p.toParties t "
      + "where f.fromPartyId=?1 and "
      + "t.toPartyId=?2")
  PrivateGroup findPrivateGroupByFromPartyIdAndToPartyId(Long fromPartyId, Long toPartyId);
  
  /**
   * This function will delete all toParty connections (only connections and not parties)
   * @param id: id of the party whose connections needs to be deleted
   */
  @Modifying
  @Query("delete from Connection c "
      + "where c.fromPartyId=?1")
  void deleteAllToPartyConnectionById(Long id);
  
}
