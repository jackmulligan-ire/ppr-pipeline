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

  public DateDetails(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
