package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Period;

public interface PeriodDao extends JpaRepository<Period, Long> {
}
