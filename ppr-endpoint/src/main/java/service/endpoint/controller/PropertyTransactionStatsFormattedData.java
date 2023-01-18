package service.endpoint.controller;

public class PropertyTransactionStatsFormattedData {
  private Integer year;
  private Integer month;
  private Float medianPrice;
  private Float meanPrice;
  private Long transactions;

  public PropertyTransactionStatsFormattedData(Integer year, Integer month, Float medianPrice, Float meanPrice, Long transactions) {
    this.year = year;
    this.month = month;
    this.medianPrice = medianPrice;
    this.meanPrice = meanPrice;
    this.transactions = transactions;
  }

  public PropertyTransactionStatsFormattedData() {
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

  public Float getMedianPrice() {
    return medianPrice;
  }

  public void setMedianPrice(Float medianPrice) {
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
