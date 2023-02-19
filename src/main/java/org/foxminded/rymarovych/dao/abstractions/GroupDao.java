package org.foxminded.rymarovych.dao.abstractions;

import org.foxminded.rymarovych.models.Group;

import java.util.Map;
import java.util.Optional;

public interface GroupDao {
    Map<Integer, Integer> getGroupIdToStudentsAmount();

    Optional<Group> getGroupById(int id);
}
