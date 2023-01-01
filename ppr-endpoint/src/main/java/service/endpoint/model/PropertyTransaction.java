package service.endpoint.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "fact_property_transactions")
public class PropertyTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "date_key")
  private Date dateKey;

  @ManyToOne
  @JoinColumn(name = "location_key")
  private Location location;

  @Column(name = "property_key")
  private int propertyKey;

  @Column(name = "transaction_price")
  private float transactionPrice;

  public PropertyTransaction() {
  }

  public PropertyTransaction(Long id, Date dateKey, Location location, int propertyKey, float transactionPrice) {
    this.id = id;
    this.dateKey = dateKey;
    this.location = location;
    this.propertyKey = propertyKey;
    this.transactionPrice = transactionPrice;
  }

  public Date getDateKey() {
    return dateKey;
  }

  public void setDateKey(Date dateKey) {
    this.dateKey = dateKey;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getPropertyKey() {
    return propertyKey;
  }

  public void setPropertyKey(int propertyKey) {
    this.propertyKey = propertyKey;
  }

  public float getTransactionPrice() {
    return transactionPrice;
  }

  public void setTransactionPrice(float transactionPrice) {
    this.transactionPrice = transactionPrice;
  }
}
