package br.com.orbitalwatch.repository;
import br.com.orbitalwatch.entity.AlertNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long> {}
