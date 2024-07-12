package org.docksidestage.bizfw.basic.buyticket;

// TODO umeyan 細かいですが、class宣言の直下、他のクラスだと一行空行を空けてますので合わせるようにお願いします by jflute (2024/07/11)
// { @code
//  public class TicketBuyResult {
//      private final Ticket ticket;
//   ↓↓↓
//  public class TicketBuyResult {
//
//      private final Ticket ticket;
//  }
// 体裁を整えるのもチーム開発では重要だと考えているゆえです。
// 参考: https://twitter.com/jflute/status/1164429226822385664

// TODO umeyan 以下のような感じで、最低限のjavadoc, よろしくお願いします by jflute (2024/07/11)
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc

public class TicketBuyResult {
    private final Ticket ticket;
    private final int change;

    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
