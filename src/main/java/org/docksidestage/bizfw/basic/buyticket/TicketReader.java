package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.clock.Clock;

// TODO umeyan javadocお願いします〜 by jflute (2024/09/03)
public class TicketReader {
    private final Clock clock;

    public TicketReader(Clock clock) {
        this.clock = clock;
    }

    public void doInPark(Ticket ticket) {
        // done umeyan 修行++: テストのために現在日時を(内部的に)差し替えられる仕組みがあると良い by jflute (2024/08/15)
        // TODO umeyan unusedが残ってるよん by jflute (2024/09/03)
        LocalDateTime jstDateTime = clock.currentJstDateTime();
        assertOutPark(ticket);
        assertUseCount(ticket);
        assertAvailableTime(ticket);
        ticket.setAvailableEnterCount(ticket.getAvailableEnterCount() - 1);
        ticket.setAlreadyIn(true);
    }

    public void doOutPark(Ticket ticket) {
        assertInPark(ticket);
        ticket.setAlreadyIn(false);
    }

    // TODO umeyan assertInPark()の定義位置、少し工夫が欲しい by jflute (2024/09/03)
    private void assertInPark(Ticket ticket) {
        if (!ticket.isAlreadyIn()) {
            throw new OutParkException("Already out park by this ticket: displayedPrice=" + ticket.getTicketType().getDisplayPrice());
        }
    }

    // TODO umeyan [いいね] privateメソッドの定義順がわかりやすい (呼び出し順と一緒になっている) by jflute (2024/09/03)
    private void assertOutPark(Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            throw new EnterParkException("Already in park by this ticket: " + ticket.getTicketType().toString());
        }
    }

    // TODO umeyan assertはassert thatのニュアンスで、UseCountがどうなるはずなのか？を示すと良い by jflute (2024/09/03)
    private void assertUseCount(Ticket ticket) {
        if (ticket.getAvailableEnterCount() <= 0) {
            throw new EnterParkException("Already cannot enter park by this ticket: availableEnterCount=" + ticket.getTicketType().geteEnterableDays());
        }
    }

    private void assertAvailableTime(Ticket ticket) {
        // TODO umeyan ここは変数抽出してくれると読み手としては嬉しいところ by jflute (2024/09/03)
        // (一番コアなロジックの部分は、シンプルな行にしたい)
        if (ticket.getTicketType().isNightOnly() && clock.currentJstDateTime().getHour() < ticket.getTicketType().getEnterableHour()) {
            throw new EnterParkException("Night only ticket cannot enter park before " + ticket.getTicketType().getEnterableHour() + ": Now=" + clock.currentJstDateTime().getHour());
        }
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
