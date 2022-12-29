package test;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "county")
  private String county;

  public Test() {}

  public Test(int id, String county) {
    this.id = id;
    this.county = county;
  }

  public int getId() {
    return id;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  //@Override
  //public String toString() {
    //return "Tutorial [id=" + id + ", county=" + county + "]";
  //}
}
