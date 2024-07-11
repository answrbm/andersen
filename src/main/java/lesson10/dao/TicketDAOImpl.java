package lesson10.dao;

import jakarta.persistence.Query;
import lesson10.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketDAOImpl implements TicketDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                s.createNativeQuery("INSERT INTO tickets(user_id, ticket_type, creation_date) " +
                                "VALUES(?, CAST(? AS ticket_type),?)",Ticket.class)
                        .setParameter(1,ticket.getUserId())
                        .setParameter(2,ticket.getTicketType())
                        .setParameter(3,ticket.getCreationDate())
                        .executeUpdate();
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
        }
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(Long ticketId) {
        Optional<Ticket> ticket = Optional.empty();
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                ticket = Optional.of(s.get(Ticket.class, ticketId));
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
            return ticket;
        }
    }

    @Override
    public List<Ticket> findByUserId(Long userId) {
        List<Ticket> tickets = new ArrayList<>();
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                Query q = s.createQuery("FROM Ticket AS T WHERE T.userId=:i", Ticket.class);
                q.setParameter("i", userId);
                for (Object ticketObj : q.getResultList()) {
                    tickets.add((Ticket) ticketObj);
                }
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
            return tickets;
        }
    }

    @Override
    public String updateTicketType(Long ticketId, String ticketType) {
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                s.createNativeQuery("UPDATE tickets SET ticket_type = CAST(? AS ticket_type) WHERE id = ?",Ticket.class)
                        .setParameter(1,ticketType)
                        .setParameter(2,ticketId)
                        .executeUpdate();
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
        }
        return ticketType;
    }

    @Override
    public Long deleteById(Long ticketId) {
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                Ticket ticket = s.get(Ticket.class, ticketId);
                s.remove(ticket);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
        }
        return ticketId;
    }
}