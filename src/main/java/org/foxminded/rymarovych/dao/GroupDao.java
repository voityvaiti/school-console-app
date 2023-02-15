package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GroupDao {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();


    public Map<Integer, Integer> getGroupIdToStudentsAmount() {

        final String GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT =
                """
                        SELECT groups.id, COUNT(students.id) AS amount_of_students
                        from students JOIN groups
                        on students.group_id = groups.id
                        GROUP BY groups.id;
                        """;

        Map<Integer, Integer> groupIdToStudentsAmount = new HashMap<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery(
                GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT)) {

            while (resultSet.next()) {
                groupIdToStudentsAmount.put(
                        resultSet.getInt("id"),
                        resultSet.getInt("amount_of_students")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupIdToStudentsAmount;
    }

    public String getGroupNameById(int id) {

        final String GET_GROUP_NAME_BY_ID_STATEMENT = "SELECT name FROM groups WHERE id=?";

        String groupName = "";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_GROUP_NAME_BY_ID_STATEMENT)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                groupName = resultSet.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupName;
    }
}
