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
import java.util.Date;
import java.util.List;

// TODO done umeyan 以下のような感じで、既存クラスで手を入れたクラスに、authorの追加をよろしくお願いします by jflute (2024/07/11)
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * @author jflute
 * @author umeda-yusuke
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // TODO umeyan [お知らせ]javadoc, 日本語で書いても全然OKです。javatryでたくさん書いていくので自分で決めてOKです by jflute (2024/07/11)
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // TODO umeyan javadoc, 変更された分の修正をしていきましょう by jflute (2024/07/11)
    // (Eclipseの方だと、いま戻り値の記述がjavadocにないというお知らせが来ています)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        checkTicketSoldOut();
        checkTicketShortMoney(handedMoney, ONE_DAY_PRICE);
        --quantity;
        processSalesProceeds(ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE, 1, false);
    }

    // TODO umeyan javadoc, 少なくともそのクラスにおける主要となるpublicメソッドをお願いします by jflute (2024/07/11)
    // これはjavadocのトレーニングのつもりで。(良いコメントを書くというのもコツがいるものなので)
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        checkTicketSoldOut();
        checkTicketShortMoney(handedMoney, TWO_DAY_PRICE);
        quantity -= 2;
        processSalesProceeds(TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE, 2, false), handedMoney - TWO_DAY_PRICE);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        checkTicketSoldOut();
        checkTicketShortMoney(handedMoney, FOUR_DAY_PRICE);
        quantity -= 4;
        processSalesProceeds(FOUR_DAY_PRICE);
        return new TicketBuyResult(new Ticket(FOUR_DAY_PRICE, 4, false), handedMoney - FOUR_DAY_PRICE);
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        checkTicketSoldOut();
        checkTicketShortMoney(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE);
        quantity -= 2;
        processSalesProceeds(NIGHT_ONLY_TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(NIGHT_ONLY_TWO_DAY_PRICE, 2, true), handedMoney - NIGHT_ONLY_TWO_DAY_PRICE);
    }

    // TODO umeyan [よもやま話]このメソッド名でも全然OKですが、よくcheckという言葉を避けようという話もあります。 by jflute (2024/07/11)
    // checkの目的語 (チェックされるもの) が「正常な方」なのか？「異常な方」なのか？どっちもありえるということが理由です。
    // 例えば、checkTicketExisting(), checkTicketSoldOut() というようにどちらでも意味が通じます。
    // その分、checkという単語だけではどっちの状態が正常なのか異常なのかが判断しづらいという面もあります。
    // この場合は文脈的に「チケット売り切れなら例外だろう」というのはパッと推測できるのですが、
    // 実務でもうちょい複雑な業務とかだと、迷うこともあったりします。
    //
    // なので、よく使われる言葉の一つの例として、assertという言葉があります。
    // assertは正常な方を目的語に書く慣習が世界的にあるので、何で例外する/しないが読み取りやすいです。
    // ここだと、assertTicketExisting() とすると「存在しなかったら例外が発生するんだな」というのが伝わります。
    //
    // 今のcheckも間違いではないので、直すかどうかは、お任せします。
    private void checkTicketSoldOut() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    private void checkTicketShortMoney(Integer handedMoney, Integer price) {
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private void processSalesProceeds(Integer price) {
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
