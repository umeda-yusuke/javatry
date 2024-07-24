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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

// TODO umeyan こちらもTicketBoothのJavaDocでの指摘と同じようにauthorお願いします by jflute (2024/07/25)
// (一つ指摘されたら、似たような箇所が他にないか？確認する習慣を付けましょう)
/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO umeyan [いいね] 横のスラスラコメント(//コメント) がとても良いですね！ by jflute (2024/07/25)
    // TODO jflute 1on1にてコメントのe.g.技の話を (2024/07/25)
    private final int displayPrice; // written on ticket, park guest can watch this
    private boolean alreadyIn = false; // true means this ticket is unavailable
    private int availableEnterCount; // 入園できる回数
    private final String ticketType; // チケットの種類
    private boolean isOnlyNight; // 夜だけ使えるチケットかどうか

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
    public Ticket(int displayPrice, int availableEnterCount, boolean isOnlyNight) {
        this.displayPrice = displayPrice;
        this.availableEnterCount = availableEnterCount;
        this.ticketType = generateTicketType(availableEnterCount);
        this.isOnlyNight = isOnlyNight;
    }

    private String generateTicketType(int availableEnterCount) {
        return String.format("%dday ticket", availableEnterCount);
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    public boolean enterPark(LocalDateTime localDateTime) {
        if (isOnlyNight && localDateTime.getHour() < 17) {
            return false;
        }
        if (availableEnterCount > 0) {
            availableEnterCount--;
            return true;
        }
        return false;
    }

    // done umeyan 配列の変数名の場合は最低限複数であることを示すのが慣習となっています。 by jflute (2024/07/11)
    // dates or dateArray (まあdatesが一般的かな)。そうすれば、for文の単一のLocalDateは素直にdateにできるかと。

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public int getAvailableEnterCount() {
        return availableEnterCount;
    }

    public String getTicketType() {
        return ticketType;
    }
}
