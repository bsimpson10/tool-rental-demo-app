package org.example.toolrental.business.rules;

import org.example.toolrental.business.dto.RentalAgreement;
import org.example.toolrental.business.rules.rule.IndependenceDayRule;
import org.example.toolrental.business.rules.rule.LaborDayRule;
import org.example.toolrental.business.rules.rule.WeekdayRule;
import org.example.toolrental.business.rules.rule.WeekendRule;
import org.example.toolrental.data.entity.Tool;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.support.ActivationRuleGroup;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultRentalCostRules implements RentalCostRules {

    private final RulesEngine rulesEngine;
    private final Rules rules;

    public DefaultRentalCostRules(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
        rules = new Rules();

        var ruleGroup = new ActivationRuleGroup(
                "cost update rules",
                "Composite of various cost updating rules"
        );

        ruleGroup.addRule(new LaborDayRule());
        ruleGroup.addRule(new IndependenceDayRule());
        ruleGroup.addRule(new WeekdayRule());
        ruleGroup.addRule(new WeekendRule());

        rules.register(ruleGroup);
    }

    @Override
    public void applyTo(final Tool tool, final RentalAgreement rentalAgreement) {
        Facts facts = new Facts();
        facts.put("agreement", rentalAgreement);
        facts.put("tool", tool);

        LocalDate date = rentalAgreement.getCheckOutDate().plusDays(1);
        while (date.isBefore(rentalAgreement.getDueDate().plusDays(1))) {
            facts.put("date", date);
            rulesEngine.fire(rules, facts);
            date = date.plusDays(1);
        }
    }
}
