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
        priority = 1,
        name = "weekday rule",
        description = "if there is a weekday charge and it is a weekday, update the cost"
)
public class WeekdayRule {
    @Condition
    public boolean itIsAWeekday(@Fact("date") LocalDate date) {
        return LocalDateUtil.isWeekday(date);
    }

    @Action
    public void updateCost(@Fact("tool") Tool tool, @Fact("agreement") RentalAgreement agreement) {
        if (tool.getType().isWeekdayCharge()) {
            agreement.setChargeableDays(agreement.getChargeableDays() + 1);
        }
    }
}
