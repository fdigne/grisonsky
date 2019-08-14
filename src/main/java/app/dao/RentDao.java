package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentDao extends JpaRepository<Rent, Long> {

    @Query(value="select o from Rent o where o.renter.id=:x")
    List<Rent> getRentsByRenterId(@Param("x") Long id);
}
