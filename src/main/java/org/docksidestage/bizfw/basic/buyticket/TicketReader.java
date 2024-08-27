package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.clock.Clock;

public class TicketReader {
    private final Clock clock;

    public TicketReader(Clock clock) {
        this.clock = clock;
    }

    public void doInPark(Ticket ticket) {
        // TODO done umeyan 修行++: テストのために現在日時を(内部的に)差し替えられる仕組みがあると良い by jflute (2024/08/15)
        LocalDateTime jstDateTime = clock.currentJstDateTime();
        if (ticket.isAlreadyIn()) {
            throw new EnterParkException("Already in park by this ticket: " + ticket.getTicketType().toString());
        }
        if (ticket.getAvailableEnterCount() <= 0) {
            throw new EnterParkException("Already cannot enter park by this ticket: availableEnterCount=" + ticket.getTicketType().geteEnterableDays());
        }
        if (ticket.getTicketType().isNightOnly() && jstDateTime.getHour() < ticket.getTicketType().getEnterableHour()) {
            throw new EnterParkException("Night only ticket cannot enter park before " + ticket.getTicketType().getEnterableHour() + ": Now=" + jstDateTime.getHour());
        }
        ticket.setAvailableEnterCount(ticket.getAvailableEnterCount() - 1);
        ticket.setAlreadyIn(true);
    }

    public void doOutPark(Ticket ticket) {
        if (!ticket.isAlreadyIn()) {
            throw new OutParkException("Already out park by this ticket: displayedPrice=" + ticket.getTicketType().getDisplayPrice());
        }
        ticket.setAlreadyIn(false);
    }

    private static class EnterParkException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        private EnterParkException(String msg) {
            super(msg);
        }
    }

    private static class OutParkException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        private OutParkException(String msg) {
            super(msg);
        }
    }
}
