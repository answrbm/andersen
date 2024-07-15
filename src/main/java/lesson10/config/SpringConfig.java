package lesson10.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ComponentScan("lesson10")
public class SpringConfig {

    @Bean
    @ConditionalOnProperty(prefix = "conditional",name = "property",havingValue = "true")
    public String thisIsMyFirstConditionalBean() {
        return "Hello from Conditional Bean!";
    }
}
