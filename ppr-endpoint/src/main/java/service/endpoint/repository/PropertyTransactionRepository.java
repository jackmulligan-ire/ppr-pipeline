package service.endpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.endpoint.model.PropertyTransaction;

public interface PropertyTransactionRepository extends JpaRepository<PropertyTransaction, Long> {
}
