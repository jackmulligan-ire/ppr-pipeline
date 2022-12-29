package test;

import org.springframework.data.jpa.repository.JpaRepository;
import test.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
