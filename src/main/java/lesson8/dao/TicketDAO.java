package lesson8.dao;

import lesson5.model.BusTicket;

import java.util.List;
import java.util.Optional;

public interface TicketDAO {

    BusTicket save(BusTicket ticket);
    Optional<BusTicket> findById(Long ticketId);
    List<BusTicket> findByUserId(Long userId);
    Long updateTicketType(Long ticketId, String ticketType);
    Long deleteById(Long ticketId);
}
