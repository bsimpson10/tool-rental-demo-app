package org.example.toolrental.business.rules.rule;

import org.example.toolrental.business.dto.RentalAgreement;
import org.example.toolrental.data.entity.Tool;
import org.example.toolrental.util.LocalDateUtil;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import java.time.LocalDate;

@Rule(
        priority = 0,
        name = "labor day rule",
        description = "if there is a holiday charge and it is labor day, update the cost"
)
public class LaborDayRule {

    @Condition
    public boolean itIsLaborDay(@Fact("date") LocalDate date) {
        return LocalDateUtil.isLaborDay(date);
    }

    @Action
    public void updateCost(@Fact("tool") Tool tool, @Fact("agreement") RentalAgreement agreement) {
        if (tool.getType().isHolidayCharge()) {
            agreement.setChargeableDays(agreement.getChargeableDays() + 1);
        }
    }
}
