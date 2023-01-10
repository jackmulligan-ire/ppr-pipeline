package service.endpoint.model;

import javax.persistence.*;

@Entity
@Table(name = "dim_location")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "location_key")
  Long id;

  @Column(name="location_county")
  String county;

  @Column(name="location_province")
  String province;

  public Location() {
  }

  public Location(Long id, String county, String province) {
    this.id = id;
    this.county = county;
    this.province = province;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }
}
