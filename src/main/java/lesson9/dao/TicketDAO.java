package lesson9.dao;

import lesson9.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDAO {

    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long ticketId);
    List<Ticket> findByUserId(Long userId);
    String updateTicketType(Long ticketId, String ticketType);
    Long deleteById(Long ticketId);
}
