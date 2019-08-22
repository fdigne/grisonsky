package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentDao extends JpaRepository<Rent, Long> {

    @Query(value="select o from Rent o where o.renter.id=:x")
    List<Rent> getRentsByRenterId(@Param("x") Long id);

    @Query(value="select sum(o.price) from Rent o where o.renter.id=:x")
    Double getServiceSumByRenterId(@Param("x") Long id);

    @Query(value="select count(*) from Rent o where o.renter.id=:x and o.cleaning=true")
    int getCleaningCountByRenterId(@Param("x") Long id);
}
