package service.endpoint.model;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;
import service.endpoint.model.pk.PropertyTransactionStatsPK;

import javax.persistence.*;

//@Immutable
//@Subselect("select gen_random_uuid() as id, pts.* from property_transaction_stats as pts")
@Entity
@Table(name = "property_transaction_stats")
@IdClass(PropertyTransactionStatsPK.class)
public class PropertyTransactionStats {
  @Id
  @Column(name = "location_county")
  private String location;

  @Id
  @Column(name = "date_year")
  private Integer year;

  @Id
  @Column(name = "date_month")
  private Integer month;

  @Column(name = "median_sale_price")
  private Long medianPrice;

  @Column(name = "mean_sale_price")
  private Float meanPrice;

  @Column(name = "num_of_transactions")
  private Long transactions;

  public PropertyTransactionStats() {
  }

  public PropertyTransactionStats(String location, Integer year, Integer month, Long medianPrice, Float meanPrice, Long transactions) {
    this.location = location;
    this.year = year;
    this.month = month;
    this.medianPrice = medianPrice;
    this.meanPrice = meanPrice;
    this.transactions = transactions;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Long getMedianPrice() {
    return medianPrice;
  }

  public void setMedianPrice(Long medianPrice) {
    this.medianPrice = medianPrice;
  }

  public Float getMeanPrice() {
    return meanPrice;
  }

  public void setMeanPrice(Float meanPrice) {
    this.meanPrice = meanPrice;
  }

  public Long getTransactions() {
    return transactions;
  }

  public void setTransactions(Long transactions) {
    this.transactions = transactions;
  }
}
