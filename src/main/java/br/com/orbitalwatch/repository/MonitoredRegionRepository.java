package br.com.orbitalwatch.repository;
import br.com.orbitalwatch.entity.MonitoredRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MonitoredRegionRepository extends JpaRepository<MonitoredRegion, Long> {}
