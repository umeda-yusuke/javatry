package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.clock.Clock;

// done umeyan javadocお願いします〜 by jflute (2024/09/03)

/**
 *  チケットリーダー
 *  チケットの読み取りを行い、入園、退園を利用回数、利用可能時間に基づいて管理する
 */
public class TicketReader {
    private final Clock clock;

    public TicketReader(Clock clock) {
        this.clock = clock;
    }

    public void doInPark(Ticket ticket) {
        // done umeyan 修行++: テストのために現在日時を(内部的に)差し替えられる仕組みがあると良い by jflute (2024/08/15)
        // done umeyan unusedが残ってるよん by jflute (2024/09/03)
        assertOutPark(ticket);
        assertAvailableEnterCountUpperZero(ticket);
        assertAvailableTime(ticket);
        ticket.setAvailableEnterCount(ticket.getAvailableEnterCount() - 1);
        ticket.setAlreadyIn(true);
    }

    public void doOutPark(Ticket ticket) {
        assertInPark(ticket);
        ticket.setAlreadyIn(false);
    }


    // done umeyan [いいね] privateメソッドの定義順がわかりやすい (呼び出し順と一緒になっている) by jflute (2024/09/03)
    private void assertOutPark(Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            throw new EnterParkException("Already in park by this ticket: " + ticket.getTicketType().toString());
        }
    }

    // done umeyan assertはassert thatのニュアンスで、UseCountがどうなるはずなのか？を示すと良い by jflute (2024/09/03)
    private void assertAvailableEnterCountUpperZero(Ticket ticket) {
        if (ticket.getAvailableEnterCount() <= 0) {
            throw new EnterParkException("Already cannot enter park by this ticket: availableEnterCount=" + ticket.getTicketType().geteEnterableDays());
        }
    }

    private void assertAvailableTime(Ticket ticket) {
        // done  umeyan ここは変数抽出してくれると読み手としては嬉しいところ by jflute (2024/09/03)
        int currentHour = clock.currentJstDateTime().getHour();
        int enterableHour = ticket.getTicketType().getEnterableHour();
        if (ticket.getTicketType().isNightOnly() && currentHour < enterableHour) {
            throw new EnterParkException("Night only ticket cannot enter park before " + enterableHour + ": Now=" + currentHour);
        }
    }

    // done umeyan assertInPark()の定義位置、少し工夫が欲しい by jflute (2024/09/03)
    private void assertInPark(Ticket ticket) {
        if (!ticket.isAlreadyIn()) {
            throw new OutParkException("Already out park by this ticket: displayedPrice=" + ticket.getTicketType().getDisplayPrice());
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
