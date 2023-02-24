package org.foxminded.rymarovych.onstartup;

import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.foxminded.rymarovych.onstartup.tablescreator.TablesCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartupPresetsExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupPresetsExecutor.class);

    private final TablesCreator tablesCreator;
    private final TablesFiller tablesFiller;

    @Autowired
    public StartupPresetsExecutor(TablesCreator tablesCreator, TablesFiller tablesFiller) {
        this.tablesCreator = tablesCreator;
        this.tablesFiller = tablesFiller;
    }

    public void runPresets() {
        LOGGER.info("Started presets execution..");

        LOGGER.debug("Tables existing ensure started");
        tablesCreator.createTablesIfNotExist();
        LOGGER.debug("Finished tables existing ensure");

        LOGGER.debug("Tables data existence ensure started");
        tablesFiller.fillTablesIfEmpty();
        LOGGER.debug("Finished tables data existence ensure");

        LOGGER.info("Presets completed");
    }
}
