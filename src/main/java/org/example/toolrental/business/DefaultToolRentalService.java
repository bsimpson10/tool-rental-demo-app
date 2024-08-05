package org.example.toolrental.business;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.example.toolrental.business.dto.RentalAgreement;
import org.example.toolrental.business.rules.RentalCostRules;
import org.example.toolrental.data.repository.ToolRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
public class DefaultToolRentalService implements ToolRentalService {

    private final RentalCostRules rentalCostRules;
    private final ToolRepository toolRepository;

    public DefaultToolRentalService(
            RentalCostRules rentalCostRules,
            ToolRepository toolRepository
    ) {
        this.rentalCostRules = rentalCostRules;
        this.toolRepository = toolRepository;
    }

    @Override
    @Transactional
    public RentalAgreement checkout(
            @NonNull String toolCode,
            @NonNull int rentalDayCount,
            @NonNull int discountPercentage,
            @NonNull LocalDate checkOutDate) {

        Preconditions.checkArgument(
                rentalDayCount > 0,
                "Rental day count must be greater than 0", rentalDayCount);
        Preconditions.checkArgument(
                discountPercentage >= 0 && discountPercentage <= 100,
                "Supplied discount percentage must be a number from 0-100", discountPercentage);

        var tool = toolRepository.findByCode(toolCode);

        Preconditions.checkNotNull(tool, "Unable to find tool with tool code = %s", toolCode);

        var agreement = new RentalAgreement(
                tool,
                rentalDayCount,
                new BigDecimal(discountPercentage)
                        .setScale(2, RoundingMode.HALF_UP)
                        .divide(new BigDecimal(100), RoundingMode.HALF_UP),
                checkOutDate
        );

        rentalCostRules.applyTo(tool, agreement);

        return agreement;
    }
}
