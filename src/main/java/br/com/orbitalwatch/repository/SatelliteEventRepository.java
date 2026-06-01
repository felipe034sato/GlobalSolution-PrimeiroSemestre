package br.com.orbitalwatch.repository;
import br.com.orbitalwatch.entity.SatelliteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SatelliteEventRepository extends JpaRepository<SatelliteEvent, Long> {}
