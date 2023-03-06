package com.myproject.service.impl;

import com.myproject.dao.GroupRepository;
import com.myproject.models.Group;
import com.myproject.models.dto.StudentAmountInGroupDto;
import com.myproject.service.abstractions.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    protected GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public String getMessageOfGroupsWithLessOrEqualsStudentsAmount(int requestedStudentsAmount) {

        StringBuilder messageBuilder = new StringBuilder();

        List<Integer> filteredGroupIdList =
                groupRepository.getGroupIdToStudentsAmount()
                        .stream()
                        .filter(dto -> dto.getAmountOfStudents() <= requestedStudentsAmount)
                        .map(StudentAmountInGroupDto::getGroupId)
                        .toList();


        if(filteredGroupIdList.isEmpty()) {
            messageBuilder.append("No such groups\n");

        } else {
            for(Integer groupId: filteredGroupIdList) {
                messageBuilder.append(
                        groupRepository.findById(groupId)
                                .stream().findAny().orElse(new Group())
                ).append("\n");
            }
        }

        return messageBuilder.toString();
    }
}
