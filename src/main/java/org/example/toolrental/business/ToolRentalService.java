package org.example.toolrental.business;

import org.example.toolrental.business.dto.RentalAgreement;

import java.time.LocalDate;

public interface ToolRentalService {

    /**
     * Produce a rental agreement
     *
     * @param toolCode is the code of the tool that is being checked out
     * @param rentalDayCount is the number of days for which the tool is to be rented
     * @param discountPercentage is the percentage discount, supplied as a whole integer value
     * @param checkOutDate is the date the tool is to be checked out
     *
     * @return a rental agreement containing relevant information to the rental
     */
    RentalAgreement checkout(
            String toolCode,
            int rentalDayCount,
            int discountPercentage,
            LocalDate checkOutDate
    );
}
