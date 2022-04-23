package vn.sapo.demo.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CalculatorHistoryRepository extends JpaRepository<CalculatorHistory, Integer> {

    @Query("select c from CalculatorHistory c where c.createdAt = ?1 and c.operator = ?2")
    List<CalculatorHistory> getCalculatorHistoryByCreatedAtAndOperator(Instant time, CalculatorHistory.Operator operator);
}
