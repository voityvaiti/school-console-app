package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.GroupRepository;
import org.foxminded.rymarovych.models.Group;
import org.foxminded.rymarovych.models.dto.StudentAmountInGroupDto;
import org.foxminded.rymarovych.service.abstractions.GroupService;
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
