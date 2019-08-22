package app.dao;

import app.domain.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RenterDao extends JpaRepository<Renter, Long> {

    @Query(value="select o from Renter o where o.name=:x")
    Renter findByName(@Param("x") String name);

}
