package lesson10;

import lesson10.config.SpringConfig;
import lesson10.service.TicketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        TicketService ticketService = applicationContext.getBean(TicketService.class);
        System.out.println(ticketService.deleteTicket(286L));

    }
}