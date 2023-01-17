package service.endpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.endpoint.model.PropertyTransactionStats;
import service.endpoint.model.pk.PropertyTransactionStatsPK;

public interface PropertyTransactionStatsRepository extends JpaRepository<PropertyTransactionStats, PropertyTransactionStatsPK> {
}
