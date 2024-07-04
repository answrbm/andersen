package lesson8.dao;

import lesson5.model.BusTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {

    private final Connection conn;

    public TicketDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public BusTicket save(BusTicket ticket) {
        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO tickets(user_id, ticket_type, creation_date) " +
                    "VALUES(?, CAST(? AS ticket_type),?)")) {
            ps.setLong(1,ticket.getUserId());
            ps.setObject(2, ticket.getTicketType());
            ps.setTimestamp(3,Timestamp.valueOf(ticket.getCreationDate()));
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return ticket;
    }

    @Override
    public Optional<BusTicket> findById(Long ticketId) {
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE id = ?")) {
            ResultSet rs = ps.executeQuery();
            conn.commit();
            if(rs.next()) {
                BusTicket ticket = new BusTicket();
                ticket.setId(rs.getLong("id"));
                ticket.setTicketType(rs.getString("ticket_type"));
                ticket.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                return Optional.of(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<BusTicket> findByUserId(Long userId) {
        List<BusTicket> tickets = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE user_id = ?")) {
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
            conn.commit();
            while(rs.next()) {
                BusTicket ticket = new BusTicket();
                ticket.setId(rs.getLong("id"));
                ticket.setTicketType(rs.getString("ticket_type"));
                ticket.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                ticket.setUserId(userId);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return tickets;
    }

    @Override
    public Long updateTicketType(Long ticketId, String ticketType) {
        try(PreparedStatement ps = conn.prepareStatement("UPDATE tickets SET ticket_type = CAST(? AS ticket_type) WHERE id = ?")) {
            ps.setString(1,ticketType);
            ps.setLong(2, ticketId);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return ticketId;
    }

    @Override
    public Long deleteById(Long ticketId) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM tickets WHERE id = ?")) {
            conn.setAutoCommit(false);
            ps.setLong(1,ticketId);
            ps.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return ticketId;
    }
}
