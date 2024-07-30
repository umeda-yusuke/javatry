package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケットの種類を持つ (1day, 2day, 4day, night only)
 * @author umeda-yusuke
 */
public enum TicketType {

    ONE_DAY(1, 7400, false),
    TWO_DAY(2, 13200, false),
    FOUR_DAY(4, 22400, false),
    NIGHT_ONLY_TWO_DAY(2, 7400, true);

    private final int days;
    private final int displayPrice;
    private final boolean isNightOnly;

    TicketType(int days, int displayPrice, boolean isNightOnly) {
        this.days = days;
        this.displayPrice = displayPrice;
        this.isNightOnly = isNightOnly;
    }

    public int getDays() {
        return days;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isNightOnly() {
        return isNightOnly;
    }
}
