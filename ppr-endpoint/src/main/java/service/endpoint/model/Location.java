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

  @Column(name="location_nuts_3")
  String nuts3;

  @Column(name="location_province")
  String province;

  public Location() {
  }

  public Location(Long id, String county, String nuts3, String province) {
    this.id = id;
    this.county = county;
    this.nuts3 = nuts3;
    this.province = province;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getNuts3() {
    return nuts3;
  }

  public void setNuts3(String nuts3) {
    this.nuts3 = nuts3;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }
}
