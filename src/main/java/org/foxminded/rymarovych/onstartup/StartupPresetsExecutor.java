package org.foxminded.rymarovych.onstartup;

import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.foxminded.rymarovych.onstartup.tablescreator.TablesCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartupPresetsExecutor {

    private final TablesCreator tablesCreator;

    private final TablesFiller tablesFiller;

    private static final Logger logger = LoggerFactory.getLogger(StartupPresetsExecutor.class);


    @Autowired
    public StartupPresetsExecutor(TablesCreator tablesCreator, TablesFiller tablesFiller) {
        this.tablesCreator = tablesCreator;
        this.tablesFiller = tablesFiller;
    }

    public void runPresets() {
        logger.info("Started presets execution..");

        logger.debug("Tables existing ensure started");
        tablesCreator.createTablesIfNotExist();
        logger.debug("Finished tables existing ensure");

        logger.debug("Tables data existence ensure started");
        tablesFiller.fillTablesIfEmpty();
        logger.debug("Finished tables data existence ensure");

        logger.info("Presets completed");
    }
}
