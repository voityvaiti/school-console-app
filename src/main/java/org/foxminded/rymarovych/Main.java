package org.foxminded.rymarovych;

import org.foxminded.rymarovych.config.SpringConfig;
import org.foxminded.rymarovych.menu.abstractions.MainMenu;
import org.foxminded.rymarovych.onstartup.TablesCreator;
import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        context.getBean(TablesCreator.class).createTablesIfNotExist();
        context.getBean(TablesFiller.class).fillTablesIfEmpty();

        context.getBean(MainMenu.class).runMainMenu();
    }
}