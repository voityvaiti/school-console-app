package org.foxminded.rymarovych;

import org.foxminded.rymarovych.menu.abstractions.MainMenu;
import org.foxminded.rymarovych.onstartup.StartupPresetsExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        context.getBean(StartupPresetsExecutor.class).runPresets();

        context.getBean(MainMenu.class).runMainMenu();
    }
}