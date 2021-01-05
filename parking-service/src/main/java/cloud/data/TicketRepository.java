package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
