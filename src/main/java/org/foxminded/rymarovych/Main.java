package org.foxminded.rymarovych;

import org.foxminded.rymarovych.config.SpringJdbcConfig;
import org.foxminded.rymarovych.onstartup.TablesCreator;
import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.foxminded.rymarovych.queryhandlers.QueryReceiver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

        new TablesCreator().createTables();
        new TablesFiller().fillTables();
        context.getBean(QueryReceiver.class).receiveAndHandleQueries();
    }
}