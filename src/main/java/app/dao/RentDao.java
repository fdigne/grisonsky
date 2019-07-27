package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Rent;

public interface RentDao extends JpaRepository<Rent, Long> {
}
