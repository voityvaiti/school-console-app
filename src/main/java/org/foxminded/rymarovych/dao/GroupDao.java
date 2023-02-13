package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GroupDao {

    private static GroupDao instance;

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private GroupDao() {
    }


    public static GroupDao getInstance() {
        if (instance == null) {
            instance = new GroupDao();
        }
        return instance;
    }

    public Map<Integer, Integer> getGroupIdToStudentsAmount() {
        Map<Integer, Integer> groupIdToStudentsAmount = new HashMap<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    """
                            SELECT groups.id, COUNT(students.id) AS amount_of_students
                            from students JOIN groups
                            on students.group_id = groups.id
                            GROUP BY groups.id;
                            """
            );

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
        String groupName = "";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT name FROM groups WHERE id=?");
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
