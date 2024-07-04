package lesson9;

import lesson9.entity.Ticket;
import lesson9.dao.TicketDAOImpl;
import lesson9.dao.UserDAOImpl;
import lesson9.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(User.class).addAnnotatedClass(Ticket.class);
        try (SessionFactory sf = conf.buildSessionFactory()) {

            UserDAOImpl userDAO = new UserDAOImpl(sf);
            TicketDAOImpl ticketDAO = new TicketDAOImpl(sf);

            User user1 = new User();
            user1.setName("John");
            user1.setCreationDate(LocalDateTime.now());
            User user2 = new User();
            user2.setName("Peter");
            user2.setCreationDate(LocalDateTime.now());
            User user3 = new User();
            user3.setName("Andrew");
            user3.setCreationDate(LocalDateTime.now());
            User user4 = new User();
            user4.setName("Smith");
            user4.setCreationDate(LocalDateTime.now());

//          userDAO.save(user1);
//          userDAO.save(user2);
//          userDAO.save(user3);
//          userDAO.save(user4);

            Ticket ticket1 = new Ticket();
            ticket1.setTicketType("DAY");
            ticket1.setUserId(userDAO.findByName(user1.getName()).get().getId());
            ticket1.setCreationDate(LocalDateTime.now());
            Ticket ticket2 = new Ticket();
            ticket2.setTicketType("WEEK");
            ticket2.setUserId(userDAO.findByName(user1.getName()).get().getId());
            ticket2.setCreationDate(LocalDateTime.now());
            Ticket ticket3 = new Ticket();
            ticket3.setTicketType("MONTH");
            ticket3.setUserId(userDAO.findByName(user2.getName()).get().getId());
            ticket3.setCreationDate(LocalDateTime.now());
            Ticket ticket4 = new Ticket();
            ticket4.setTicketType("YEAR");
            ticket4.setUserId(userDAO.findByName(user2.getName()).get().getId());
            ticket4.setCreationDate(LocalDateTime.now());
            Ticket ticket5 = new Ticket();
            ticket5.setTicketType("DAY");
            ticket5.setUserId(userDAO.findByName(user3.getName()).get().getId());
            ticket5.setCreationDate(LocalDateTime.now());
            Ticket ticket6 = new Ticket();
            ticket6.setTicketType("YEAR");
            ticket6.setUserId(userDAO.findByName(user4.getName()).get().getId());
            ticket6.setCreationDate(LocalDateTime.now());

//          ticketDAO.save(ticket1);
//          ticketDAO.save(ticket2);
//          ticketDAO.save(ticket3);
//          ticketDAO.save(ticket4);
//          ticketDAO.save(ticket5);
//          ticketDAO.save(ticket6);

            System.out.println(userDAO.findById(4L).get());
            System.out.println(ticketDAO.updateTicketType(ticketDAO.findById(10L).get().getId(), "DAY"));
            System.out.println(ticketDAO.findByUserId(4L));

        }
    }
}
