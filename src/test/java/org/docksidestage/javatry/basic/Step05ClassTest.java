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
package org.docksidestage.javatry.basic;

import java.time.LocalDate;
import java.util.Date;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author umeda-yusuke
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400); // salesProceeds = 7400, quantity = 9
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
        // 合ってた。buyOneDayPassport内では、quantityが１減り、salesProceedsに値が代入される。
        // その後、getQuantityでquantityの値を取得している。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000); // salesProceeds=10000, quantity=9
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000
        // 合ってた。salesProceedsの初期値はnullなので、salesProceedsに値が代入される。
        // その後、getSalesProceedsでsalesProceedsの値を取得している。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
        // 合ってた。salesProceedsの初期値はnullなので、そのまま取得している。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9
        // 合ってた。buyOneDayPassport内でquantityが1減り、例外が発生する。
        // その後、getQuantityでquantityの値を取得している。
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
        // できた。
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
        // できた
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // uncomment after making the method
//        TicketBooth booth = new TicketBooth();
//        int money = 14000;
//        int change = booth.buyTwoDayPassport(money);
//        Integer sea = booth.getSalesProceeds() + change;
//        log(sea); // should be same as money
//
//        // and show two-day passport quantity here
//        log("two-day passport quantity: " + booth.getQuantity());
        // できた
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
        // できた
        // checkTicketSoldOutは売り切れていないかを確認するメソッド
        // checkTicketShortMoneyはお金が足りているかを確認するメソッド
        // processSalesProceedsは売り上げを計算するメソッド
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
        // できた。alreadyInの初期値は未設定でもfalseだが、明示的にfalseを設定している。
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        LocalDate day1 = LocalDate.of(2024, 7, 10);
        LocalDate day2 = LocalDate.of(2024, 7, 11);
        LocalDate day3 = LocalDate.of(2024, 7, 12);

        TicketBooth booth = new TicketBooth();
        booth.selectTwoDate(day1, day2); // 2日指定する
        TicketBuyResult buyResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyResult.getTicket();

        log("入場可能日の確認");
        log(twoDayPassport.isAvailableDate(day1)); // should be true
        log(twoDayPassport.isAvailableDate(day2)); // should be true
        log(twoDayPassport.isAvailableDate(day3)); // should be false

        log("入場後の確認");
        log(twoDayPassport.enterPark(day1)); // should be true
        log(twoDayPassport.enterPark(day1)); // should be false
        log(twoDayPassport.enterPark(day2)); // should be true
        log(twoDayPassport.enterPark(day2)); // should be false
        log(twoDayPassport.enterPark(day3)); // should be false
    }
    // TwoDayPassportをどう解釈するかで、実装はかなり変わると思う。
//    ・　単純に２回入ることができる
//    ・　1日1回入場できる　
//    ・　決まった日なら何回でも入場できる。そしてその日が2日ある
//    元の実装が、一回外に出て、再入場ができない仕様なので、一度入ったら基本的に外に出ない施設と仮定。つまり、一日1回入場できるという仕様になる。
//    よって、決まった日に１回だけ入場できる仕様にする。
//    日にちの指定は、チケットを買うタイミングで指定する仕様にする。日には重複がないようにする。

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        showTicketIfNeeds(oneDayPassport);
        TicketBuyResult buyResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }
    // 色んな判断基準が考えられるが、今回は値段で判断することにする。ガバガバ条件だが、1万円以上のいチケットはTwoDayPassportとする。

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        if (ticket.getDisplayPrice() > 10000) { // write determination for two-day passport
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        // your confirmation code here
        LocalDate day1 = LocalDate.of(2024, 7, 10);
        LocalDate day2 = LocalDate.of(2024, 7, 11);
        LocalDate day3 = LocalDate.of(2024, 7, 12);
        LocalDate day4 = LocalDate.of(2024, 7, 13);

        TicketBooth booth = new TicketBooth();
        booth.selectFourDate(day1, day2, day3, day4); // 4日指定する
        TicketBuyResult buyResult = booth.buyFourDayPassport(30000);
        Ticket fourDayPassport = buyResult.getTicket();

        log(fourDayPassport.getDisplayPrice() + buyResult.getChange()); // should be same as money
    }
//    方針
//    ・　buyTwoDayPassportを拡張して、multipleDayPassportを作る
//    ・　buyFourDayPassportを作る
//    TicketBooth内部で、selectDateを２つ作るのは、違和感があるので、selectDateの長さを４に固定する
    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }
}
