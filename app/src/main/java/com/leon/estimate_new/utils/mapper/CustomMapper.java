package com.leon.estimate_new.utils.mapper;

import com.leon.estimate_new.fragments.forms.BaseInfoViewModel;
import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    PersonalViewModel examinerDutyToPersonalViewModel(ExaminerDuties examinerDuty);

    BaseInfoViewModel examinerDutyBaseInfoViewModel(ExaminerDuties examinerDuty);

    void updateExaminerDutyPersonalViewModel(PersonalViewModel personalViewModel,
                                             @MappingTarget ExaminerDuties examinerDuty);

    void updateToCalculationUserInputFromPersonalViewModel(PersonalViewModel personalVM,
                                                           @MappingTarget CalculationUserInput calculationUserInput);

    void updateExaminerDutyBaseInfoViewModel(BaseInfoViewModel baseInfoViewModel,
                                             @MappingTarget ExaminerDuties examinerDuty);

    void updateCalculationUserInputBaseInfoViewModel(BaseInfoViewModel baseInfoViewModel,
                                                     @MappingTarget CalculationUserInput calculationUserInput);

}
