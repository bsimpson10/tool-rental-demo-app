package org.example.toolrental.data.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ToolType {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tool_type_seq"
    )
    @SequenceGenerator(
            name = "tool_type_seq",
            allocationSize = 5
    )
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private BigDecimal dailyCharge;

    @Column
    private boolean isWeekdayCharge;

    @Column
    private boolean isWeekendCharge;

    @Column
    private boolean isHolidayCharge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return isWeekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        isWeekdayCharge = weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return isWeekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        isWeekendCharge = weekendCharge;
    }

    public boolean isHolidayCharge() {
        return isHolidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        isHolidayCharge = holidayCharge;
    }
}
