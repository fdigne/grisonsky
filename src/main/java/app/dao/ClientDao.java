package app.dao;

import app.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientDao extends JpaRepository<Client, Long> {
}
