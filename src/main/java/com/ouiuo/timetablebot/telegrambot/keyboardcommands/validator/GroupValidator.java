package com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator;

import com.ouiuo.timetablebot.model.Group;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ouiuo.timetablebot.model.state.enums.States.SELECT_GROUP;
import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.Entities.GROUP;

@Component
@RequiredArgsConstructor
public class GroupValidator implements Validator {
    private final GroupService groupService;

    @Override
    public ValidationResult validate(String msg) {
        ValidationResult validationResult = new ValidationResultImpl();

        String[] split = msg.split("[ -]");
        if (split.length != 2) {
            validationResult.getErrorMessages().add("Не верный разделитель, используйте или пробел или -");
            return validationResult;
        }
        List<Group> groupByNameAndNumber;
        int groupNumber;
        String groupName = split[0].toUpperCase();
        try {
            groupNumber = Integer.parseInt(split[1]);
            groupByNameAndNumber = groupService.findGroupByNameAndNumber(groupName, groupNumber);
        } catch (NumberFormatException e) {
            validationResult.getErrorMessages().add("Не верный номер группы");
            return validationResult;
        }

        if (groupByNameAndNumber.isEmpty()) {
            String replace = groupName.replace("ОZ", "OZ");
            groupByNameAndNumber = groupService.findGroupByNameAndNumber(replace, groupNumber);
            if (groupByNameAndNumber.isEmpty()) {
                validationResult.getErrorMessages().add("По указанным данным группа не найдена");
            }
            return validationResult;
        }
        if (groupByNameAndNumber.size() != 1) {
            validationResult.getErrorMessages().add("По указанным данным найдено несколько групп");
            return validationResult;
        }
        validationResult.getParsedMap().put(GROUP, groupByNameAndNumber.get(0));
        return validationResult;
    }

    @Override
    public States getState() {
        return SELECT_GROUP;
    }
}
