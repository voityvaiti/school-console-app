package org.foxminded.rymarovych.dao.repository;

import org.foxminded.rymarovych.models.Group;
import org.foxminded.rymarovych.models.dto.StudentAmountInGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("""
            SELECT NEW org.foxminded.rymarovych.models.dto.StudentAmountInGroupDto(g.id, COUNT(g.id)) 
            FROM Group g, Student s WHERE g.id = s.groupId GROUP BY g.id
            """)
    List<StudentAmountInGroupDto> getGroupIdToStudentsAmount();
}
