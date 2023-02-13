package org.foxminded.rymarovych.onstartup;

import org.foxminded.rymarovych.service.database.SQLScriptRunner;

import java.nio.file.Paths;

public class TablesCreator {

    private static final String CREATE_TABLES_SCRIPT_PATH =
            "src/main/resources/sql/CREATE_TABLES.sql";
    public void createTables() {
        SQLScriptRunner sqlScriptRunner = new SQLScriptRunner();
        sqlScriptRunner.runSqlScript(Paths.get(CREATE_TABLES_SCRIPT_PATH));
    }
}
