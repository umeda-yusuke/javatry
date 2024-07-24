package org.docksidestage.bizfw.basic.buyticket;

// done umeyan 細かいですが、class宣言の直下、他のクラスだと一行空行を空けてますので合わせるようにお願いします by jflute (2024/07/11)
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

// done umeyan 以下のような感じで、最低限のjavadoc, よろしくお願いします by jflute (2024/07/11)
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc

// TODO umeyan [いいね]javadoc, 良いですね！概要が一行あるだけでも全然初見の人の理解しやすさが変わりますね by jflute (2024/07/25)
// TODO jflute 1on1にて、コメントで具体的な項目を列挙する場合のリスクと工夫について補足予定 (2024/07/25)

// TODO umeyan ちょっと勘違いさせてしまったかもですが、クラス宣言のクラスjavadocの間は空行は無しでOKです by jflute (2024/07/25)
// (↑の指摘は、クラス宣言の下の方の話なので)
/**
 * チケットの購入結果を表すクラス。チケットオブジェクトとおつりを保持する。
 * @author umeda-yusuke
 */

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
