package org.foxminded.rymarovych.onstartup.tablefiller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TablesFiller {

    private final DataSource dataSource;

    private final CoursesTableFiller coursesTableFiller;
    private final GroupsTableFiller groupsTableFiller;
    private final StudentsCoursesTableFiller studentsCoursesTableFiller;
    private final StudentsTableFiller studentsTableFiller;

    @Autowired
    public TablesFiller(DataSource dataSource, CoursesTableFiller coursesTableFiller, GroupsTableFiller groupsTableFiller, StudentsCoursesTableFiller studentsCoursesTableFiller, StudentsTableFiller studentsTableFiller) {
        this.dataSource = dataSource;
        this.coursesTableFiller = coursesTableFiller;
        this.groupsTableFiller = groupsTableFiller;
        this.studentsCoursesTableFiller = studentsCoursesTableFiller;
        this.studentsTableFiller = studentsTableFiller;
    }

    public void fillTablesIfEmpty() {

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(coursesTableFiller.generateStatement());
            statement.execute(groupsTableFiller.generateStatement());
            statement.execute(studentsCoursesTableFiller.generateStatement());
            statement.execute(studentsTableFiller.generateStatement());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
