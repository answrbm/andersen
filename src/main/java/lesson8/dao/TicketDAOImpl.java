package lesson8.dao;

import lesson5.model.BusTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {

    @Override
    public BusTicket save(BusTicket ticket) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tickets(user_id, ticket_type, creation_date) " +
                    "VALUES(?, CAST(? AS ticket_type),?)")) {
            ps.setLong(1,ticket.getUserId());
            ps.setObject(2, ticket.getTicketType());
            ps.setTimestamp(3,Timestamp.valueOf(ticket.getCreationDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public Optional<BusTicket> findById(Long ticketId) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE id = ?")) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                BusTicket ticket = new BusTicket();
                ticket.setId(rs.getLong("id"));
                ticket.setTicketType(rs.getString("ticket_type"));
                ticket.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                return Optional.of(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BusTicket> findByUserId(Long userId) {
        List<BusTicket> tickets = new ArrayList<>();
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE user_id = ?")) {
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
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
        }
        return tickets;
    }

    @Override
    public Long deleteById(Long ticketId) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tickets WHERE id = ?")) {
            ps.setLong(1,ticketId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketId;
    }
}
