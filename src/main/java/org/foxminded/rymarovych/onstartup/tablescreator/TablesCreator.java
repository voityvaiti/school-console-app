package org.foxminded.rymarovych.onstartup.tablescreator;

import org.foxminded.rymarovych.utility.database.SQLScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class TablesCreator {

    private static final Logger logger = LoggerFactory.getLogger(TablesCreator.class);

    private static final String CREATE_TABLES_IF_NOT_EXIST_SCRIPT_PATH =
            "src/main/resources/sql/CREATE_TABLES_IF_NOT_EXIST.sql";
    public void createTablesIfNotExist() {
        SQLScriptRunner sqlScriptRunner = new SQLScriptRunner();

        logger.debug("Running tables existence ensure statement...");

        sqlScriptRunner.runSqlScript(Paths.get(CREATE_TABLES_IF_NOT_EXIST_SCRIPT_PATH));

        logger.debug("Tables existence ensure statement finished");
    }
}
