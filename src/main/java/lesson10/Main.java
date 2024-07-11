package lesson10;

import lesson10.config.SpringConfig;
import lesson10.dao.TicketService;
import lesson10.dao.UserService;
import lesson10.model.Ticket;
import lesson10.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        Environment env = applicationContext.getEnvironment();
        UserService userService = applicationContext.getBean(UserService.class);
        TicketService ticketService = applicationContext.getBean(TicketService.class);

        Resource ticketResource = applicationContext.getResource("file:"+env.getProperty("file"));

        System.out.println(ticketService.getBusTickets(ticketResource));
        User updatedUser = new User();
        updatedUser.setName("AdamUpdated");
        updatedUser.setCreationDate(LocalDateTime.now());
        Ticket ticketToCreate = new Ticket();
        ticketToCreate.setTicketType("DAY");
        ticketToCreate.setCreationDate(LocalDateTime.now());
        System.out.println(userService.updateUserAndCreateTicketForUser(1L,updatedUser,ticketToCreate));

    }
}