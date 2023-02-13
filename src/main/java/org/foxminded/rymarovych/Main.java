package org.foxminded.rymarovych;

import org.foxminded.rymarovych.dao.StudentCourseDao;
import org.foxminded.rymarovych.onstartup.TablesCreator;
import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;
import org.foxminded.rymarovych.queryhandlers.QueryReceiver;

public class Main {
    public static void main(String[] args) {
        new TablesCreator().createTables();
        new TablesFiller().fillTables();

        new QueryReceiver().receiveAndHandleQueries();
    }
}