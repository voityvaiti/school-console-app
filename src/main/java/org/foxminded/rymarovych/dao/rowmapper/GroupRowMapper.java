package org.foxminded.rymarovych.dao.rowmapper;

import org.foxminded.rymarovych.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();

        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));

        return group;
    }
}
