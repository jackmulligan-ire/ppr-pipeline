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

  public PropertyDetails() {
  }

  public PropertyDetails(Long id, String buildType) {
    this.id = id;
    this.buildType = buildType;
  }

  public String getBuildType() {
    return buildType;
  }

  public void setBuildType(String buildType) {
    this.buildType = buildType;
  }

}
