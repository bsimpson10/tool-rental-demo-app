package org.example.toolrental.business.rules;

import org.example.toolrental.business.dto.RentalAgreement;
import org.example.toolrental.data.entity.Tool;

import java.math.BigDecimal;

public interface RentalCostRules {

    /**
     * Apply rental cost rules to an existing rental agreement
     * in order to calculate all value totals
     *
     * @param tool is the selected tool for which the agreement was created
     * @param rentalAgreement is the rental agreement that is being populated
     */
    void applyTo(Tool tool, RentalAgreement rentalAgreement);
}
