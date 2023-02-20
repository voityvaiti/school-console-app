package org.foxminded.rymarovych;

import org.foxminded.rymarovych.onstartup.TablesCreator;
import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.foxminded.rymarovych.queryhandlers.QueryReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        new TablesCreator().createTables();
        new TablesFiller().fillTables();

        new QueryReceiver().receiveAndHandleQueries();
    }
}