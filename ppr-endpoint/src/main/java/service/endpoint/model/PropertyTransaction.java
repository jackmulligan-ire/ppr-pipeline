package service.endpoint.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "fact_property_transactions")
public class PropertyTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date dateKey;

  private int locationKey;

  private int propertyKey;

  private float transactionPrice;

  public PropertyTransaction(Date dateKey, int locationKey, int propertyKey, float transactionPrice) {
    this.dateKey = dateKey;
    this.locationKey = locationKey;
    this.propertyKey = propertyKey;
    this.transactionPrice = transactionPrice;
  }

  public PropertyTransaction() {}

  public Long getId() {return id;}

  public Date getDate() {return dateKey;}

  public void setDate(Date dateKey) {this.dateKey = dateKey;}

  public int getLocationKey() {return locationKey;}

  public void setLocationKey(int locationKey) {this.locationKey = locationKey;}

  public int getPropertyKey() {return propertyKey;}

  public void setPropertyKey(int propertyKey) {this.propertyKey = propertyKey;}

  public float getTransactionPrice(){return transactionPrice;}

  public void setTransactionPrice(float transactionPrice) {this.transactionPrice = transactionPrice;}
}
