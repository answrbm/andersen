package lesson10.util;

import lesson10.model.Ticket;
import lesson10.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SessionFactoryManager {

    private final SessionFactory SF;

    public SessionFactoryManager() {
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(Ticket.class);
        conf.addAnnotatedClass(User.class);
        SF = conf.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return SF;
    }
}
