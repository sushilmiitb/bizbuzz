package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author mukesh
 *
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {
  private static final long serialVersionUID = 7548489633277523780L;
  
  @Id
  @GeneratedValue
  private Long id;
  @Embedded
  @AttributeOverrides( {
      @AttributeOverride(name="toName", column=@Column(name="fromAddressToName")),
      @AttributeOverride(name="address1", column=@Column(name="fromAddressAddress1")),
      @AttributeOverride(name="address2", column=@Column(name="fromAddressAddress2")),
      @AttributeOverride(name="landmark", column=@Column(name="fromAddressLandmark")),
      @AttributeOverride(name="city", column=@Column(name="fromAddressCity")),
      @AttributeOverride(name="postalCode", column=@Column(name="fromAddressPostalCode")),
      @AttributeOverride(name="stateGeoId", column=@Column(name="fromAddressStateGeoId")),
      @AttributeOverride(name="phoneNumber", column=@Column(name="fromAddressPhoneNumber"))
  })
  private OrderAddress fromAddress;
  @Embedded
  @AttributeOverrides( {
    @AttributeOverride(name="toName", column=@Column(name="toAddressToName")),
    @AttributeOverride(name="address1", column=@Column(name="toAddressAddress1")),
    @AttributeOverride(name="address2", column=@Column(name="toAddressAddress2")),
    @AttributeOverride(name="landmark", column=@Column(name="toAddressLandmark")),
    @AttributeOverride(name="city", column=@Column(name="toAddressCity")),
    @AttributeOverride(name="postalCode", column=@Column(name="toAddressPostalCode")),
    @AttributeOverride(name="stateGeoId", column=@Column(name="toAddressStateGeoId")),
    @AttributeOverride(name="phoneNumber", column=@Column(name="toAddressPhoneNumber"))
})
  private OrderAddress toAddress;
  
  private String goodsTypeSpecificName;
  private String insuranceType;
  /**
   * Following fields will be used to take preference of the user for trucks
   * while ordering it.
   */
  private String makeCompany;
  private String modelNumber;
  private String genericType; //what is particular type of truck commonly called as
  private String numberOfWheels;
  private Long lengthFeet;
  private Long heightFeet;
  private Long widthFeet;
  private Long gwt; //gross weight tonnage
  private Long permittedTonnage; //weight that is permitted for truck to carry
  private Long chassisType;
  
  @Column(name ="pickupDate")
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern="MM/dd/yyyy")
  private Date pickupDate;
  
  @Column(name ="pickupTime")
  @Temporal(TemporalType.TIME)
  @DateTimeFormat(style="-S")
  private Date pickUpTime;
  
  public String getMakeCompany() {
    return makeCompany;
  }
  public void setMakeCompany(String makeCompany) {
    this.makeCompany = makeCompany;
  }
  public String getModelNumber() {
    return modelNumber;
  }
  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }
  public String getGenericType() {
    return genericType;
  }
  public void setGenericType(String genericType) {
    this.genericType = genericType;
  }
  public String getNumberOfWheels() {
    return numberOfWheels;
  }
  public void setNumberOfWheels(String numberOfWheels) {
    this.numberOfWheels = numberOfWheels;
  }
  public Long getLengthFeet() {
    return lengthFeet;
  }
  public void setLengthFeet(Long lengthFeet) {
    this.lengthFeet = lengthFeet;
  }
  public Long getHeightFeet() {
    return heightFeet;
  }
  public void setHeightFeet(Long heightFeet) {
    this.heightFeet = heightFeet;
  }
  public Long getWidthFeet() {
    return widthFeet;
  }
  public void setWidthFeet(Long widthFeet) {
    this.widthFeet = widthFeet;
  }
  public Long getGwt() {
    return gwt;
  }
  public void setGwt(Long gwt) {
    this.gwt = gwt;
  }
  public Long getPermittedTonnage() {
    return permittedTonnage;
  }
  public void setPermittedTonnage(Long permittedTonnage) {
    this.permittedTonnage = permittedTonnage;
  }
  public Date getPickupDate() {
    return pickupDate;
  }
  public void setPickupDate(Date pickupDate) {
    this.pickupDate = pickupDate;
  }
  public Date getPickUpTime() {
    return pickUpTime;
  }
  public void setPickUpTime(Date pickUpTime) {
    this.pickUpTime = pickUpTime;
  }
  public Long getChassisType() {
    return chassisType;
  }
  public void setChassisType(Long chassisType) {
    this.chassisType = chassisType;
  }
  
  @ManyToOne
  @JoinColumn(name="truck_id")
  private Truck truck;
  @ManyToOne
  @JoinColumn(name="driver_id")
  private Person driver;
  @ManyToOne
  @JoinColumn(name="from_party_id")
  private Party fromParty;
  @ManyToOne
  @JoinColumn(name="to_party_id")
  private Party toParty;
  private Date dropTime;
  
  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date updatedAt;
  public OrderAddress getFromAddress() {
    return fromAddress;
  }
  public void setFromAddress(OrderAddress fromAddress) {
    this.fromAddress = fromAddress;
  }
  public OrderAddress getToAddress() {
    return toAddress;
  }
  public void setToAddress(OrderAddress toAddress) {
    this.toAddress = toAddress;
  }
  public String getGoodsTypeSpecificName() {
    return goodsTypeSpecificName;
  }
  public void setGoodsTypeSpecificName(String goodsTypeSpecificName) {
    this.goodsTypeSpecificName = goodsTypeSpecificName;
  }
  public String getInsuranceType() {
    return insuranceType;
  }
  public void setInsuranceType(String insuranceType) {
    this.insuranceType = insuranceType;
  }
  public Truck getTruck() {
    return truck;
  }
  public void setTruck(Truck truck) {
    this.truck = truck;
  }
  public Person getDriver() {
    return driver;
  }
  public void setDriver(Person driver) {
    this.driver = driver;
  }
  public Party getFromParty() {
    return fromParty;
  }
  public void setFromParty(Party fromParty) {
    this.fromParty = fromParty;
  }
  public Party getToParty() {
    return toParty;
  }
  public void setToParty(Party toParty) {
    this.toParty = toParty;
  }
  
  public Date getDropTime() {
    return dropTime;
  }
  public void setDropTime(Date dropTime) {
    this.dropTime = dropTime;
  }
  public Long getId() {
    return id;
  }
  public Date getCreatedAt() {
    return createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
}
