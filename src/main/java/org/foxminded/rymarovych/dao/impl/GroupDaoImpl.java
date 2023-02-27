package org.foxminded.rymarovych.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.foxminded.rymarovych.dao.abstractions.GroupDao;
import org.foxminded.rymarovych.models.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupDaoImpl implements GroupDao {

    public static final String GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT =
            """
                    SELECT g.id, COUNT(g.id)
                    FROM Group g, Student s WHERE g.id = s.groupId
                    GROUP BY g.id
                           """;
    public static final String FIND_GROUP_BY_ID_STATEMENT = "SELECT g FROM Group g WHERE g.id = :id";


    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<Integer, Integer> getGroupIdToStudentsAmount() {
        LOGGER.debug("Attempt to get 'group id to students amount' map");

        List<Object[]> queryResult = entityManager.createQuery(GET_GROUPS_ID_AND_AMOUNT_OF_ITS_STUDENTS_STATEMENT)
                .getResultList();

        Map<Integer, Integer> groupIdToStudentsAmountMap = new HashMap<>();

        final int GROUP_ID_INDEX = 0;
        final int STUDENTS_AMOUNT_INDEX = 1;

        queryResult.forEach(row -> groupIdToStudentsAmountMap.put((Integer) row[GROUP_ID_INDEX],
                ((Long) row[STUDENTS_AMOUNT_INDEX]).intValue()));

        LOGGER.debug("'Group id to students amount' map received. Size: {}", groupIdToStudentsAmountMap.size());

        return groupIdToStudentsAmountMap;
    }

    @Override
    public Optional<Group> findGroupById(int id) {
        LOGGER.debug("Attempt to find group by ID: {}", id);
        try {
            Group group = entityManager.createQuery(FIND_GROUP_BY_ID_STATEMENT, Group.class)
                    .setParameter("id", id)
                    .getSingleResult();

            LOGGER.debug("Found group ({}) by ID: {}", group, id);
            return Optional.of(group);

        } catch (NoResultException e) {

            LOGGER.warn("Group not found by ID: {}", id);
            return Optional.empty();
        }
    }

}
