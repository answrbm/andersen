package lesson8;

import lesson5.model.BusTicket;
import lesson8.dao.TicketDAOImpl;
import lesson8.dao.UserDAOImpl;
import lesson8.model.UserImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        try(Connection conn = lesson8.Connection.connect()) {
            conn.setAutoCommit(false);
            UserDAOImpl userDAO = new UserDAOImpl(conn);
            TicketDAOImpl ticketDAO = new TicketDAOImpl(conn);

            UserImpl user1 = new UserImpl();
            user1.setName("Adam");
            user1.setCreationDate(LocalDateTime.now());
            UserImpl user2 = new UserImpl();
            user2.setName("Bob");
            user2.setCreationDate(LocalDateTime.now());
            UserImpl user3 = new UserImpl();
            user3.setName("Sam");
            user3.setCreationDate(LocalDateTime.now());
            UserImpl user4 = new UserImpl();
            user4.setName("Mark");
            user4.setCreationDate(LocalDateTime.now());

//        userDAO.save(user1);
//        userDAO.save(user2);
//        userDAO.save(user3);
//        userDAO.save(user4);

            BusTicket ticket1 = new BusTicket();
            ticket1.setTicketType("DAY");
            ticket1.setUserId(1L);
            ticket1.setCreationDate(LocalDateTime.now());
            BusTicket ticket2 = new BusTicket();
            ticket2.setTicketType("WEEK");
            ticket2.setUserId(1L);
            ticket2.setCreationDate(LocalDateTime.now());
            BusTicket ticket3 = new BusTicket();
            ticket3.setTicketType("MONTH");
            ticket3.setUserId(2L);
            ticket3.setCreationDate(LocalDateTime.now());
            BusTicket ticket4 = new BusTicket();
            ticket4.setTicketType("YEAR");
            ticket4.setUserId(2L);
            ticket4.setCreationDate(LocalDateTime.now());
            BusTicket ticket5 = new BusTicket();
            ticket5.setTicketType("DAY");
            ticket5.setUserId(3L);
            ticket5.setCreationDate(LocalDateTime.now());
            BusTicket ticket6 = new BusTicket();
            ticket6.setTicketType("WEEK");
            ticket6.setUserId(4L);
            ticket6.setCreationDate(LocalDateTime.now());

//        ticketDAO.save(ticket1);
//        ticketDAO.save(ticket2);
//        ticketDAO.save(ticket3);
//        ticketDAO.save(ticket4);
//        ticketDAO.save(ticket5);
//        ticketDAO.save(ticket6);

            System.out.println(userDAO.findById(3L).get());
            System.out.println(userDAO.deleteById(3L));
            System.out.println(ticketDAO.findByUserId(3L));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
