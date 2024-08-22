/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDateTime;
import java.time.ZoneId;

// done umeyan ↑一度指摘されたら、他にも似たところがないか確認する習慣を by jflute (2024/07/26)

// done umeyan こちらもTicketBoothのJavaDocでの指摘と同じようにauthorお願いします by jflute (2024/07/25)
// (一つ指摘されたら、似たような箇所が他にないか？確認する習慣を付けましょう)
/**
 * @author jflute
 * @author umeda-yusuke
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done umeyan [いいね] 横のスラスラコメント(//コメント) がとても良いですね！ by jflute (2024/07/25)
    // done jflute 1on1にてコメントのe.g.技の話を (2024/07/25)
    // [memo] チケットの種類 e.g. 1day ticket
    // done umeyan [思考課題] インスタンス変数の定義順序って、どうしたらいいか？ってアイディアありますか？ by jflute (2024/07/31)
    // 重要な物順とかですかね？何を持って重要かは分からないですが、雰囲気で並び変えてみます by umeyan (2024/07/31)
    private final TicketType ticketType; // チケットの種類
    private int availableEnterCount; // 入園できる回数
    private boolean alreadyIn = false; // true means this ticket is unavailable

    // done umeyan この変数はpublicで公開する必要はないと思うのでprivateにしましょう by jflute (2024/07/11)
    // インスタンス変数をpublicにすることはめったにないです。(publicフィールドスタイルの場合は別ですが)
    // done umeyan HashMapを扱う時は、Mapインターフェースで受け取るのが慣習です by jflute (2024/07/11)
    // step6のオブジェクト指向のところでポリモーフィズムで詳しくやるのですが...
    // このMapを利用する側は、「Hashで実現したMap」であることを意識する必要はないので、それをMapで隠蔽します。
    // new ArrayList()をListインターフェース受け取るのと同じです。

    // 日付は使わない方針にしたので、Hashmapを使わない方針に変更
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
        this.availableEnterCount = ticketType.geteEnterableDays();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // done umeyan 元々のdoInPark()を修正してenterPark()の内容を実装してしまってOKです by jflute (2024/07/31)
    // ただ、LocalDateTimeの引数は、チケットの利用者が日時 (現在日時) を指定するのは変なので、現在日時は中で取りたいですね。
    public void doInPark() {
        // TODO umeyan 修行++: テストのために現在日時を(内部的に)差し替えられる仕組みがあると良い by jflute (2024/08/15)
        LocalDateTime jstDateTime = getJstDateTime();
        // done umeyan せっかくなのでticketTypeごと例外メッセージに載せてしまっても良いと思います by jflute (2024/07/31)
        if (alreadyIn) {
            throw new EnterParkException("Already in park by this ticket: " + ticketType.toString());
        }
        if (availableEnterCount <= 0) {
            throw new EnterParkException("Already cannot enter park by this ticket: availableEnterCount=" + ticketType.geteEnterableDays());
        }
        // [memo] 業務例外の話をちょこっとだけ by jflute
        // done umeyan 17という定義をできればハードコードしたくない... by jflute (2024/08/15)
        // というか、今後もっと色々な基準時刻のパターンのnightOnlyのチケットが増えた時、スムーズに追加できるようにしたい
        if (ticketType.isNightOnly() && jstDateTime.getHour() < ticketType.getEnterableHour()) {
            throw new EnterParkException("Night only ticket cannot enter park before " + ticketType.getEnterableHour() + ": Now=" + jstDateTime.getHour());
        }
        availableEnterCount--;
        alreadyIn = true;
        
        // done umeyan [読み物課題] 思い出した、このブログを読んでみてください by jflute (2024/07/31)
        // // 例外メッセージ、敬語で満足でもロスロスパターン
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
    }

    public void doOutPark() {
        if (!alreadyIn) {
            throw new OutParkException("Already out park by this ticket: displayedPrice=" + ticketType.getDisplayPrice());
        }
        alreadyIn = false;
    }

    // done umeyan 配列の変数名の場合は最低限複数であることを示すのが慣習となっています。 by jflute (2024/07/11)
    // dates or dateArray (まあdatesが一般的かな)。そうすれば、for文の単一のLocalDateは素直にdateにできるかと。

    public LocalDateTime getJstDateTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
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

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // done umeyan インスタンス変数の順番に合わせましょう by jflute (2024/07/31)
    public TicketType getTicketType() {
        return ticketType;
    }

    public int getDisplayPrice() { // ちょいFacade的なメソッド
        return ticketType.getDisplayPrice();
    }

    public int getAvailableEnterCount() {
        return availableEnterCount;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
