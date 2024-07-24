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

// TODO umeyan unusedのimportがあります by jflute (2024/07/25)
// IntelliJで、shift+command+A の後に Organize imports のコマンドを実行すると消えると思いますのでぜひ使ってみてください。

// done umeyan 以下のような感じで、既存クラスで手を入れたクラスに、authorの追加をよろしくお願いします by jflute (2024/07/11)
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
    // done umeyan [お知らせ]javadoc, 日本語で書いても全然OKです。javatryでたくさん書いていくので自分で決めてOKです by jflute (2024/07/11)
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // TODO umeyan これはjavatryでのお願いですが、引数と戻り値に関しては、nullの可否を明示するようにお願いします by jflute (2024/07/25)
    // handedMoneyの方はすでに書いてあるので、ここでは戻り値の方ですね。
    //
    // @return 1Dayパスポートのチケット
    //  ↓
    // @return 1Dayパスポートのチケット (NotNull)
    // 
    // Javaの性質上、どうしても "値がないかもしれない" (nullかもしれない) というのを意識しないといけないので、
    // メソッドの呼び出し側からすると、引数や戻り値でnullがあり得るのか？ってのは大きな関心事になります。
    // というか厳密にはそれを正確に把握した上で呼び出さないといけないという感じですね。
    // (Java8のOptionalに関しては、step8でやりますのでここではnull限定の話で)
    // なので、JavaDocにnullの可否が書いてあるというのは、とてもありがたい情報なのです。
    //
    // ぼくが書くJavaDocではほとんど (NotNull) もしくは (NullAllowed) と付けていますし、
    // 書き方が違えど、Javaの標準APIのクラスのJavaDocでもnullの可否が書いてあるものもあります。
    // (LocalDate@plusDays() の JavaDoc の Returns の部分を読んでみてください)
    // ということで、これはあくまでjfluteのオススメということになりますが、
    // 少なくともくぼBootCampのjavatryやハンズオンでは面倒でも書いてもらって、null可否の意識を高めてもらいたいという狙いがあります。
    //
    // (intとかbooleanのプリミティブ型は、そもそも文法的にnullにならないのでその場合は要らないですが)
    // _/_/_/_/_/_/_/_/_/_/
     /**
     * 1Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
      * @return 1Dayパスポートのチケット
     */
    // done umeyan javadoc, 変更された分の修正をしていきましょう by jflute (2024/07/11)
    // (Eclipseの方だと、いま戻り値の記述がjavadocにないというお知らせが来ています)
    public Ticket buyOneDayPassport(Integer handedMoney) {
        assertTicketExisting();
        assertEnoughMoney(handedMoney, ONE_DAY_PRICE);
        --quantity;
        processSalesProceeds(ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE, 1, false);
    }

    // done umeyan javadoc, 少なくともそのクラスにおける主要となるpublicメソッドをお願いします by jflute (2024/07/11)
    // これはjavadocのトレーニングのつもりで。(良いコメントを書くというのもコツがいるものなので)
    /**
     * 2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     * @return 2Dayパスポートのチケットの購入結果
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        assertTicketExisting();
        assertEnoughMoney(handedMoney, TWO_DAY_PRICE);
        quantity -= 2;
        processSalesProceeds(TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE, 2, false), handedMoney - TWO_DAY_PRICE);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        assertTicketExisting();
        assertEnoughMoney(handedMoney, FOUR_DAY_PRICE);
        quantity -= 4;
        processSalesProceeds(FOUR_DAY_PRICE);
        return new TicketBuyResult(new Ticket(FOUR_DAY_PRICE, 4, false), handedMoney - FOUR_DAY_PRICE);
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        assertTicketExisting();
        assertEnoughMoney(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE);
        quantity -= 2;
        processSalesProceeds(NIGHT_ONLY_TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(NIGHT_ONLY_TWO_DAY_PRICE, 2, true), handedMoney - NIGHT_ONLY_TWO_DAY_PRICE);
    }

    // done umeyan [よもやま話]このメソッド名でも全然OKですが、よくcheckという言葉を避けようという話もあります。 by jflute (2024/07/11)
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

    // なるほど！今後assertをたくさん使っていこうと思います！ by umeda-yusuke（2024/07/24）
    private void assertTicketExisting() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    // お金が足りているか確認するので、assertEnoughMoney() とする by umeda-yusuke（2024/07/24）
    private void assertEnoughMoney(Integer handedMoney, Integer price) {
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
