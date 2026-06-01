package br.com.orbitalwatch.repository;
import br.com.orbitalwatch.entity.SatelliteMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SatelliteMissionRepository extends JpaRepository<SatelliteMission, Long> {}
