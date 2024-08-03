package com.leon.estimate_new.utils.mapper;

import com.leon.estimate_new.fragments.dialog.AddDocumentViewModel;
import com.leon.estimate_new.fragments.dialog.TejarihaSayerViewModel;
import com.leon.estimate_new.fragments.forms.BaseInfoViewModel;
import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.fragments.forms.TechnicalInfoViewModel;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.Tejariha;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    Tejariha tejarihaToTejarihaViewModel(TejarihaSayerViewModel tejarihaSayerVM);

    @Mapping(source = "isNewEnsheab", target = "newEnsheab")
    PersonalViewModel examinerDutyToPersonalViewModel(ExaminerDuties examinerDuty);

    @Mapping(source = "isEnsheabQeirDaem", target = "ensheabQeirDaem")
    BaseInfoViewModel examinerDutyBaseInfoViewModel(ExaminerDuties examinerDuty);

    void updateExaminerDutyPersonalViewModel(PersonalViewModel personalViewModel,
                                             @MappingTarget ExaminerDuties examinerDuty);

    void updateToCalculationUserInputFromPersonalViewModel(PersonalViewModel personalVM,
                                                           @MappingTarget CalculationUserInput calculationUserInput);

    @Mapping(source = "ensheabQeirDaem", target = "isEnsheabQeirDaem")
    void updateExaminerDutyBaseInfoViewModel(BaseInfoViewModel baseInfoViewModel,
                                             @MappingTarget ExaminerDuties examinerDuty);

    void updateCalculationUserInputBaseInfoViewModel(BaseInfoViewModel baseInfoViewModel,
                                                     @MappingTarget CalculationUserInput calculationUserInput);

    void updateExaminerDutyTechnicalInfoViewModel(TechnicalInfoViewModel technicalInfoViewModel,
                                                  @MappingTarget ExaminerDuties examinerDuties);

    @Mapping(source = "isNewEnsheab", target = "newEnsheab")
    TechnicalInfoViewModel examinerDutyToTechnicalInfoViewModel(ExaminerDuties examinerDuty);


    AddDocumentViewModel examinerDutyToAddDocumentViewModel(ExaminerDuties examinerDuty);

}
