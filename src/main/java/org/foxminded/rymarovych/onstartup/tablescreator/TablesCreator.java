package org.foxminded.rymarovych.onstartup.tablescreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TablesCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TablesCreator.class);

    private static final String TABLES_INIT_SCRIPT_PATH =
            "src/main/resources/db/script/TABLES_INIT.sql";


    private final DataSource dataSource;

    public TablesCreator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTablesIfNotExist() {

        String sqlScript;

        try {
            sqlScript = Files.readString(Path.of(TABLES_INIT_SCRIPT_PATH));

        } catch (IOException e) {
            LOGGER.error("Error when reading sql script by path: {}", TABLES_INIT_SCRIPT_PATH);
            throw new RuntimeException(e);
        }

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sqlScript);

        } catch (SQLException e) {
            LOGGER.error("Error when executing sql script: {}", sqlScript);
            throw new RuntimeException(e);
        }

    }
}
