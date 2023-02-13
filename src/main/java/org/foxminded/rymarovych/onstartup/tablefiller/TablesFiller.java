package org.foxminded.rymarovych.onstartup.tablefiller;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TablesFiller {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    public void fillTables() {

        try(Statement statement = connection.createStatement() ) {
            statement.execute(new CoursesTableFiller().generateStatement());
            statement.execute(new GroupsTableFiller().generateStatement());
            statement.execute(new StudentsCoursesTableFiller().generateStatement());
            statement.execute(new StudentsTableFiller().generateStatement());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
