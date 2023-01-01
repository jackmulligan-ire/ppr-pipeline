package service.endpoint.model;

import javax.persistence.*;

@Entity
@Table(name = "dim_property")
public class PropertyDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "property_key")
  Long id;

  @Column(name = "property_build_type")
  String buildType;

  @Column(name = "property_market_price")
  String marketPrice;

  @Column(name = "property_vat_exclusive")
  String vatExclusive;

  @Column(name = "property_size")
  String propertySize;

  public PropertyDetails() {
  }

  public PropertyDetails(Long id, String buildType, String marketPrice, String vatExclusive, String propertySize) {
    this.id = id;
    this.buildType = buildType;
    this.marketPrice = marketPrice;
    this.vatExclusive = vatExclusive;
    this.propertySize = propertySize;
  }

  public String getBuildType() {
    return buildType;
  }

  public void setBuildType(String buildType) {
    this.buildType = buildType;
  }

  public String getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(String marketPrice) {
    this.marketPrice = marketPrice;
  }

  public String getVatExclusive() {
    return vatExclusive;
  }

  public void setVatExclusive(String vatExclusive) {
    this.vatExclusive = vatExclusive;
  }

  public String getPropertySize() {
    return propertySize;
  }

  public void setPropertySize(String propertySize) {
    this.propertySize = propertySize;
  }
}
