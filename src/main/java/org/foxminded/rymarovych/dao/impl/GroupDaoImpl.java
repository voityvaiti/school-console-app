package org.foxminded.rymarovych.dao.impl;

import org.foxminded.rymarovych.dao.abstractions.GroupDao;
import org.foxminded.rymarovych.dao.rowmapper.GroupRowMapper;
import org.foxminded.rymarovych.models.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GroupDaoImpl implements GroupDao {

    public static final String GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT =
            """
                    SELECT groups.id, COUNT(students.id) AS amount_of_students
                    FROM students JOIN groups
                    ON students.group_id = groups.id
                    GROUP BY groups.id;
                           """;
    public static final String GET_GROUP_BY_ID_STATEMENT = "SELECT * FROM groups WHERE id=?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Group> groupRowMapper = new GroupRowMapper();

    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<Integer, Integer> getGroupIdToStudentsAmount() {
        return jdbcTemplate.queryForStream(GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT, new RowMapper<Map.Entry<Integer, Integer>>() {
            @Override
            public Map.Entry<Integer, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AbstractMap.SimpleEntry<>(
                        rs.getInt("id"),
                        rs.getInt("amount_of_students")
                );
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Optional<Group> findGroupById(int id) {
        Group group = jdbcTemplate
                .query(GET_GROUP_BY_ID_STATEMENT, groupRowMapper, id)
                .stream().findAny().orElse(null);

        if (group == null) {
            return Optional.empty();
        } else {
            return Optional.of(group);
        }
    }

}
