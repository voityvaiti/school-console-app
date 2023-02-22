package org.foxminded.rymarovych.onstartup;

import org.foxminded.rymarovych.utility.database.SQLScriptRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class TablesCreator {

    private static final String CREATE_TABLES_IF_NOT_EXIST_SCRIPT_PATH =
            "src/main/resources/sql/CREATE_TABLES_IF_NOT_EXIST.sql";
    public void createTablesIfNotExist() {
        SQLScriptRunner sqlScriptRunner = new SQLScriptRunner();
        sqlScriptRunner.runSqlScript(Paths.get(CREATE_TABLES_IF_NOT_EXIST_SCRIPT_PATH));
    }
}
