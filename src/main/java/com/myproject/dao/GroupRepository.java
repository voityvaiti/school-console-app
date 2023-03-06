package com.myproject.dao;

import com.myproject.models.Group;
import com.myproject.models.dto.StudentAmountInGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("""
            SELECT NEW com.myproject.models.dto.StudentAmountInGroupDto(g.id, COUNT(g.id)) 
            FROM Group g, Student s WHERE g.id = s.groupId GROUP BY g.id
            """)
    List<StudentAmountInGroupDto> getGroupIdToStudentsAmount();
}
