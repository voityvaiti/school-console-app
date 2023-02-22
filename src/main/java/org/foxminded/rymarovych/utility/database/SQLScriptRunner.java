package org.foxminded.rymarovych.utility.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLScriptRunner {
    public void runSqlScript(Path sqlScriptPath) {

        String sqlScript;

        try {
            sqlScript = Files.readString(sqlScriptPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Statement statement = DatabaseConnector.getInstance().getConnection().createStatement()){
            statement.execute(sqlScript);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
