package service.endpoint.model.pk;

import java.io.Serializable;
import java.util.Objects;

public class PropertyTransactionStatsPK implements Serializable {
  private String location;

  private Integer year;

  private Integer month;

  public PropertyTransactionStatsPK() {
  }

  public PropertyTransactionStatsPK(String location, Integer year, Integer month) {
    this.location = location;
    this.year = year;
    this.month = month;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PropertyTransactionStatsPK that = (PropertyTransactionStatsPK) o;
    return location.equals(that.location) && year.equals(that.year) && month.equals(that.month);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, year, month);
  }
}
