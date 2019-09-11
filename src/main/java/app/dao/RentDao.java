package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RentDao extends JpaRepository<Rent, Long> {

    @Query(value="select o from Rent o where o.renter.id=:x order by o.period.startDate asc")
    List<Rent> getRentsByRenterId(@Param("x") Long id);

    @Query(value="select sum(o.price) from Rent o where o.renter.id=:x and o.period.endDate <:y and o.paid=false")
    Double getServiceSumByRenterId(@Param("x") Long id, @Param("y") Date date);

    @Query(value="select count(*) from Rent o where o.renter.id=:x and o.cleaning=true and o.period.endDate <:y and o.paid=false")
    int getCleaningCountByRenterId(@Param("x") Long id, @Param("y") Date date);

    @Query(value="select o from Rent o order by o.period.startDate asc")
    List<Rent> findAllOrderByDate();
}
