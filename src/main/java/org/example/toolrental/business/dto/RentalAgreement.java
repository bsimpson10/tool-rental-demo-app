package org.example.toolrental.business.dto;

import org.example.toolrental.data.entity.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class RentalAgreement {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");

    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final LocalDate checkOutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyRentalCharge;
    private int chargeableDays;
    private final BigDecimal discountMultiplier;

    public RentalAgreement(Tool tool, int rentalDays, BigDecimal discountMultiplier, LocalDate checkOutDate) {
        this.toolCode = tool.getCode();
        this.toolType = tool.getType().getName();
        this.toolBrand = tool.getBrand().getName();
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = checkOutDate.plusDays(rentalDays);
        this.dailyRentalCharge = tool.getType().getDailyCharge();
        this.chargeableDays = 0;
        this.discountMultiplier = discountMultiplier;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeableDays() {
        return chargeableDays;
    }

    public void setChargeableDays(int chargeableDays) {
        this.chargeableDays = chargeableDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return dailyRentalCharge.multiply(new BigDecimal(chargeableDays));
    }

    public BigDecimal getDiscountMultiplier() {
        return discountMultiplier;
    }

    public BigDecimal getDiscountAmount() {
        return discountMultiplier
                .multiply(getPreDiscountCharge()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFinalCharge() {
        return this.getPreDiscountCharge().subtract(this.getDiscountAmount());
    }

    public void println() {

        var builder = new StringBuilder();

        builder.append("Tool code: ");
        builder.append(this.toolCode);
        builder.append("\nTool type: ");
        builder.append(this.toolType);
        builder.append("\nTool brand: ");
        builder.append(this.toolBrand);
        builder.append("\nRental days: ");
        builder.append(this.rentalDays);
        builder.append("\nCheck out date: ");
        builder.append(this.checkOutDate.format(DATE_FORMATTER));
        builder.append("\nDue date: ");
        builder.append(this.dueDate.format(DATE_FORMATTER));
        builder.append("\nDaily rental charge: ");
        builder.append(NumberFormat.getCurrencyInstance().format(this.dailyRentalCharge));
        builder.append("\nChargeable days: ");
        builder.append(this.chargeableDays);
        builder.append("\nPre-discount charge: ");
        builder.append(NumberFormat.getCurrencyInstance().format(this.getPreDiscountCharge()));
        builder.append("\nDiscount percentage: ");
        builder.append(this.discountMultiplier.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP)).append("%");
        builder.append("\nDiscount amount: ");
        builder.append(NumberFormat.getCurrencyInstance().format(this.getDiscountAmount()));
        builder.append("\nFinal charge: ");
        builder.append(NumberFormat.getCurrencyInstance().format(this.getFinalCharge()));
        builder.append("\n");

        System.out.println(builder);
    }
}
