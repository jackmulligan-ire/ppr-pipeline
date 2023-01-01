package service.endpoint.model;

import javax.persistence.*;

@Entity
@Table(name = "fact_property_transactions")
public class PropertyTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "date_key")
  private DateDetails dateDetails;

  @ManyToOne
  @JoinColumn(name = "location_key")
  private Location location;

  @ManyToOne
  @JoinColumn(name = "property_key")
  private PropertyDetails propertyDetails;

  @Column(name = "transaction_price")
  private float transactionPrice;

  public PropertyTransaction() {
  }

  public PropertyTransaction(Long id, DateDetails dateDetails, Location location, PropertyDetails propertyDetails, float transactionPrice) {
    this.id = id;
    this.dateDetails = dateDetails;
    this.location = location;
    this.propertyDetails = propertyDetails;
    this.transactionPrice = transactionPrice;
  }

  public DateDetails getDateDetails() {
    return dateDetails;
  }

  public void setDateDetails(DateDetails dateDetails) {
    this.dateDetails = dateDetails;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public PropertyDetails getPropertyDetails() {
    return propertyDetails;
  }

  public void setPropertyDetails(PropertyDetails propertyDetails) {
    this.propertyDetails = propertyDetails;
  }

  public float getTransactionPrice() {
    return transactionPrice;
  }

  public void setTransactionPrice(float transactionPrice) {
    this.transactionPrice = transactionPrice;
  }
}
