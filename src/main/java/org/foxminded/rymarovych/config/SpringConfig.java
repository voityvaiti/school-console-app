package org.foxminded.rymarovych.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.foxminded.rymarovych")
@PropertySource("classpath:db/db.properties")
public class SpringConfig {

    private final Environment env;

    public SpringConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("URL"));
        dataSource.setUsername(env.getProperty("USER"));
        dataSource.setPassword(env.getProperty("PASSWORD"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(postgresqlDataSource());
    }
}
