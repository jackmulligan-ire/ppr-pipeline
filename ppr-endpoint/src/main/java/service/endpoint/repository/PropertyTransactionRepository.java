package service.endpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.endpoint.model.PropertyTransaction;

import java.sql.Date;
import java.util.List;

public interface PropertyTransactionRepository extends JpaRepository<PropertyTransaction, Long> {
  List<PropertyTransaction> findByDateDetails_DateAfter(Date date);

  List<PropertyTransaction> findByDateDetails_DateBefore(Date date);

  List<PropertyTransaction> findByDateDetails_DateBetween(Date startDate, Date endDate);
}
