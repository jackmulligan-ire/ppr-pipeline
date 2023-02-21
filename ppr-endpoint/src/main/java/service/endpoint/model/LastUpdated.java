package service.endpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "last_updated")
public class LastUpdated {
  @Id
  private Long id;

  @Column(name ="update")
  private Timestamp update;

  public LastUpdated() {
  }

  public LastUpdated(Long id, Timestamp update) {
    this.id = id;
    this.update = update;
  }

  public Timestamp getUpdate() {
    return update;
  }

  public void setUpdate(Timestamp update) {
    this.update = update;
  }
}
