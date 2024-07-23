package com.leon.estimate_new.utils.mapper;

import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    PersonalViewModel examinerDutyToPersonalVM(ExaminerDuties examinerDuty);
    PersonalViewModel examinerDutyToPersonalVM1(ExaminerDuties examinerDuty);

    ExaminerDuties personalVMToExaminerDuty(PersonalViewModel personalVM);

    CalculationUserInput personalVMToCalculationUserInput(PersonalViewModel personalVM);

    void updateExaminerDutyPersonalVM(PersonalViewModel personalVM,
                                      @MappingTarget ExaminerDuties examinerDuty);
    void updateToCalculationUserInputFromPersonVM(PersonalViewModel personalVM,
                                                  @MappingTarget CalculationUserInput calculationUserInput);
}
