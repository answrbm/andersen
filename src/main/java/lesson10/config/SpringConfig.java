package lesson10.config;

import lesson10.model.Ticket;
import lesson10.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("application.properties")
@ComponentScan("lesson10")
public class SpringConfig {

    private final Environment env;

    @Autowired
    public SpringConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
        conf.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        conf.setProperty("hibernate.connection.driver_class",env.getProperty("hibernate.connection.driver_class"));
        conf.setProperty("hibernate.connection.url",env.getProperty("hibernate.connection.url"));
        conf.setProperty("hibernate.connection.username",env.getProperty("hibernate.connection.username"));
        conf.setProperty("hibernate.connection.password",env.getProperty("hibernate.connection.password"));
        conf.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        conf.setProperty("hibernate.hbm2ddl",env.getProperty("hibernate.hbm2ddl"));
        conf.addAnnotatedClass(Ticket.class);
        conf.addAnnotatedClass(User.class);

        return conf.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sessionFactory());

        return transactionManager;
    }
}
