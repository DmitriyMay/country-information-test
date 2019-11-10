package main.com.test.task.aleshin.dmitriy.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private String hibernateConfig = "hibernate.cfg.xml";

    @Bean
    public SessionFactory sessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(hibernateConfig).build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory factory =  metadata.getSessionFactoryBuilder().build();
        return factory;
    }
}
