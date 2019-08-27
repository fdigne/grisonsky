package app.dao;

import app.domain.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RenterDao extends JpaRepository<Renter, Long> {

    @Query(value="select o from Renter o where o.name=:x")
    Renter findByName(@Param("x") String name);

    @Query(value="select o from Renter o where o.id not in (select r.id from Renter r where r.id =:x)")
    List<Renter> getRenters(@Param("x") Long id);

}
