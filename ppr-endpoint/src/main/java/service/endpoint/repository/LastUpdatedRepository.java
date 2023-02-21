package service.endpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.endpoint.model.LastUpdated;

import java.util.List;

public interface LastUpdatedRepository extends JpaRepository<LastUpdated, Long> {
  List<LastUpdated> findAllByOrderByUpdateDesc();
}
