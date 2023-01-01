package service.endpoint.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dim_date")
public class DateDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "date_key")
  private Date date;

  @Column(name = "date_month")
  private Integer month;

  @Column(name = "date_quarter")
  private Integer quarter;

  @Column(name = "date_year")
  private Integer year;

  public DateDetails() {
  }

  public DateDetails(Date date, Integer month, Integer quarter, Integer year) {
    this.date = date;
    this.month = month;
    this.quarter = quarter;
    this.year = year;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getQuarter() {
    return quarter;
  }

  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }
}
