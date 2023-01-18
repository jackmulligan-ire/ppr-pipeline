package service.endpoint.controller;

import java.util.ArrayList;
import java.util.List;

public class PropertyTransactionStatsFormatted {
  private String location;
  private List<PropertyTransactionStatsFormattedData> data = new ArrayList<>();

  public PropertyTransactionStatsFormatted() {
  }

  public PropertyTransactionStatsFormatted(String location, List<PropertyTransactionStatsFormattedData> data) {
    this.location = location;
    this.data = data;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<PropertyTransactionStatsFormattedData> getData() {
    return data;
  }

  public void setData(List<PropertyTransactionStatsFormattedData> data) {
    this.data = data;
  }
}
