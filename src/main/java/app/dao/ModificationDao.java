package app.dao;

import app.domain.Modification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ModificationDao extends JpaRepository<Modification, Long> {

    @Query(value="select o from Modification o where o.date in (select max(date) from Modification)")
    Modification findLastModification();
}
