package lesson10.repository;


import jakarta.transaction.Transactional;
import lesson10.model.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {

    @Modifying
    @Query(value = "INSERT INTO tickets(user_id, ticket_type, creation_date) VALUES(:user_id, CAST(:ticket_type AS ticket_type), :creation_date)",
            nativeQuery = true)
    Integer save(@Param("user_id") Long userId, @Param("ticket_type") String ticketType, @Param("creation_date") LocalDateTime creationDate);

    @Modifying
    @Query(value = "UPDATE tickets SET user_id = :user_id, ticket_type = CAST(:ticket_type AS ticket_type), creation_date = :creation_date WHERE id = :ticket_id",
            nativeQuery = true)
    Integer update(@Param("ticket_id") Long ticketId, @Param("user_id") Long userId, @Param("ticket_type") String ticketType, @Param("creation_date") LocalDateTime creationDate);

    List<Ticket> findByUserId(Long userId);
}
