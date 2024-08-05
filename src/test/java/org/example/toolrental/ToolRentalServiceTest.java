package org.example.toolrental;

import org.example.toolrental.business.ToolRentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles({"default", "test"})
public class ToolRentalServiceTest {

    @Autowired
    ToolRentalService toolRentalService;

    @Test
    public void discountPercentageOutOfBoundsTest() {
        assertThatThrownBy(
                () -> toolRentalService.checkout(
                        "CHNS",
                        5,
                        101,
                        LocalDate.of(2015, 9, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("Supplied discount percentage must be a number from 0-100");

    }

    @Test
    public void independenceDayLadderTest() {
        var agreement = toolRentalService.checkout(
                        "LADW",
                        3,
                        10,
                        LocalDate.of(2020, 7, 2));
        agreement.println();
        assertThat(agreement.getToolCode()).isEqualTo("LADW");
        assertThat(agreement.getToolType()).isEqualTo("Ladder");
        assertThat(agreement.getToolBrand()).isEqualTo("Werner");
        assertThat(agreement.getRentalDays()).isEqualTo(3);
        assertThat(agreement.getCheckOutDate()).isEqualTo(LocalDate.of(2020, 7, 2));
        assertThat(agreement.getDueDate()).isEqualTo(LocalDate.of(2020, 7, 5));
        assertThat(agreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("1.99"));
        assertThat(agreement.getChargeableDays()).isEqualTo(2);
        assertThat(agreement.getDiscountMultiplier()
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal("10"));
        assertThat(agreement.getPreDiscountCharge()).isEqualTo(new BigDecimal("3.98"));
        assertThat(agreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.40"));
        assertThat(agreement.getFinalCharge()).isEqualTo(new BigDecimal("3.58"));
    }

    @Test
    public void independenceDayChainsawTest() {
        var agreement = toolRentalService.checkout(
                "CHNS",
                5,
                25,
                LocalDate.of(2015, 7, 2));
        agreement.println();
        assertThat(agreement.getToolCode()).isEqualTo("CHNS");
        assertThat(agreement.getToolType()).isEqualTo("Chainsaw");
        assertThat(agreement.getToolBrand()).isEqualTo("Stihl");
        assertThat(agreement.getRentalDays()).isEqualTo(5);
        assertThat(agreement.getCheckOutDate()).isEqualTo(LocalDate.of(2015, 7, 2));
        assertThat(agreement.getDueDate()).isEqualTo(LocalDate.of(2015, 7, 7));
        assertThat(agreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("1.49"));
        assertThat(agreement.getChargeableDays()).isEqualTo(3);
        assertThat(agreement.getDiscountMultiplier()
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal("25"));
        assertThat(agreement.getPreDiscountCharge()).isEqualTo(new BigDecimal("4.47"));
        assertThat(agreement.getDiscountAmount()).isEqualTo(new BigDecimal("1.12"));
        assertThat(agreement.getFinalCharge()).isEqualTo(new BigDecimal("3.35"));
    }

    @Test
    public void laborDayJackhammerTest() {
        var agreement = toolRentalService.checkout(
                "JAKD",
                6,
                0,
                LocalDate.of(2015, 9, 3));
        agreement.println();
        assertThat(agreement.getToolCode()).isEqualTo("JAKD");
        assertThat(agreement.getToolType()).isEqualTo("Jackhammer");
        assertThat(agreement.getToolBrand()).isEqualTo("DeWalt");
        assertThat(agreement.getRentalDays()).isEqualTo(6);
        assertThat(agreement.getCheckOutDate()).isEqualTo(LocalDate.of(2015, 9, 3));
        assertThat(agreement.getDueDate()).isEqualTo(LocalDate.of(2015, 9, 9));
        assertThat(agreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(agreement.getChargeableDays()).isEqualTo(3);
        assertThat(agreement.getDiscountMultiplier()
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal("0"));
        assertThat(agreement.getPreDiscountCharge()).isEqualTo(new BigDecimal("8.97"));
        assertThat(agreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.00"));
        assertThat(agreement.getFinalCharge()).isEqualTo(new BigDecimal("8.97"));
    }

    @Test
    public void independenceDayJackhammerTest() {
        var agreement = toolRentalService.checkout(
                "JAKR",
                9,
                0,
                LocalDate.of(2015, 7, 2));
        agreement.println();
        assertThat(agreement.getToolCode()).isEqualTo("JAKR");
        assertThat(agreement.getToolType()).isEqualTo("Jackhammer");
        assertThat(agreement.getToolBrand()).isEqualTo("Ridgid");
        assertThat(agreement.getRentalDays()).isEqualTo(9);
        assertThat(agreement.getCheckOutDate()).isEqualTo(LocalDate.of(2015, 7, 2));
        assertThat(agreement.getDueDate()).isEqualTo(LocalDate.of(2015, 7, 11));
        assertThat(agreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(agreement.getChargeableDays()).isEqualTo(5);
        assertThat(agreement.getDiscountMultiplier()
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal("0"));
        assertThat(agreement.getPreDiscountCharge()).isEqualTo(new BigDecimal("14.95"));
        assertThat(agreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.00"));
        assertThat(agreement.getFinalCharge()).isEqualTo(new BigDecimal("14.95"));
    }

    @Test
    public void independenceDayChainsawTestHalfOff() {
        var agreement = toolRentalService.checkout(
                "JAKR",
                4,
                50,
                LocalDate.of(2015, 7, 2));
        agreement.println();
        assertThat(agreement.getToolCode()).isEqualTo("JAKR");
        assertThat(agreement.getToolType()).isEqualTo("Jackhammer");
        assertThat(agreement.getToolBrand()).isEqualTo("Ridgid");
        assertThat(agreement.getRentalDays()).isEqualTo(4);
        assertThat(agreement.getCheckOutDate()).isEqualTo(LocalDate.of(2015, 7, 2));
        assertThat(agreement.getDueDate()).isEqualTo(LocalDate.of(2015, 7, 6));
        assertThat(agreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(agreement.getChargeableDays()).isEqualTo(1);
        assertThat(agreement.getDiscountMultiplier()
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal("50"));
        assertThat(agreement.getPreDiscountCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(agreement.getDiscountAmount()).isEqualTo(new BigDecimal("1.50"));
        assertThat(agreement.getFinalCharge()).isEqualTo(new BigDecimal("1.49"));
    }
}
