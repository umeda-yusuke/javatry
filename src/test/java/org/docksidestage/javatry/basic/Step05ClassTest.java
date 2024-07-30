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

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.unit.PlainTestCase;

// done umeyan ↑unusedなimport by jflute (2024/07/11)

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

    // done umeyan [読み物課題] プログラマーに求められるデザイン脳 by jflute (2024/07/18)
    // https://jflute.hatenadiary.jp/entry/20170623/desigraming
    // done jflute 1on1にてちょい補足 (2024/07/25)
    
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
        // done umeyan [いいね]良い単位でまとめっていると思います！buyOneDayとbuyTwoDayが流れがわかりやすく少しスッキリしました by jflute (2024/07/11)

        // TODO done umeyan [思考課題] おつりの計算ですが、なんたることかおつりを10円増やすというサービス仕様に変えると仮定します by jflute (2024/07/25)
        // そのとき、どこをどう直しますか？できるだけこういうときは一箇所だけ直せばOKという風にしたいものです。(1on1のとき聞きます)
        // [memo] 説明したので、いい感じの実装をお願いします。

        // calculateChangeメソッドを作成し、おつりの計算を行うように変更する。by umeda (2024/07/30)

        // TODO done umeyan [思考課題] 在庫を減らした後、売上計上の前のタイミングで、ログを出力したいと言われたと仮定します by jflute (2024/07/25)
        // そのとき、どこにそのログ処理を入れますか？できるだけこういうときは一箇所だけ直せばOKという風にしたいものです。(1on1のとき聞きます)
        // [memo] がんば！
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
        Ticket oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
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
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyResult.getTicket();
        for (int i = 0; i <= 3; i++) {
            log("残り入園回数：" + twoDayPassport.getAvailableEnterCount());
            twoDayPassport.enterPark(LocalDateTime.now());
        }
    }
    // TwoDayPassportをどう解釈するかで、実装はかなり変わると思う。
//    ・　単純に２回入ることができる
//    ・　1日1回入場できる　
//    ・　決まった日なら何回でも入場できる。そしてその日が2日ある
//    元の実装が、一回外に出て、再入場ができない仕様なので、一度入ったら基本的に外に出ない施設と仮定。つまり、一日1回入場できるという仕様になる。
//    よって、決まった日に１回だけ入場できる仕様にする。
//    日にちの指定は、チケットを買うタイミングで指定する仕様にする。日には重複がないようにする。
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // done umeyan [ふぉろー]色々と考えて頂き、ありがとうございます。色々仕様を想定するのもトレーニングなので素晴らしいです。 by jflute (2024/07/11)
    // 多くの人は「単純に２回入ることができる」という形で実装しています。その単純な仕様でもそれなりに苦しめるエクササイズになっているので(^^
    // 実質的には「一日1回入場」とほぼ同じで、イメージとしては外に出る場合は再入場券を別途もらうことで、チケットの状態はそのままみたいな。
    // (TwoDayと言ってますから、仕組みとしては単純に2回入るでも、業務上は別日に入ることを想定)
    // ただ、チケット入場が1日1回だけというチェックがあると親切ですよね。明日の分入れなくなっちゃったー（＞＜、ってならないように。
    //
    // 一方で、「決まった日なら何回でも入場できる。そしてその日が2日ある」というのは、またパターンが２つあるかなと。
    // A. チケットを買うときから入場できる日を決めているのか？
    //  (チケットに入場できる日が固定で書かているイメージ、この場合は別にTwoDayだけじゃなくOneDayもあり得ることかな)
    // B. 入場した日なら何回でも入場できるのか？
    //  (21日の朝に思い立って1度入場して21日は何度も入場できて、また別日(あと1日)にいつか気が向いた時に入場する)
    //  (ただ、これは一日1回入場のケースと近くて、同日2回目入場時にエラーになるのか？素通りできるのか？の違い)
    //
    // すでにやりかけの実装もあるわけなので、うめやんさん自身で決めちゃって良いです。それに合わせてぼくもレビューをしてきます。
    // ただ、もう一つの選択肢としては、段階実装というのもあるかなと。
    // まずは「単純に２回入ることができる」でキチっと実装。それができたら1日1回の入場チェック。そのあと日付...
    // という感じで。
    // _/_/_/_/_/_/_/_/_/_/

    // 了解です！単純に２回入る事ができる仕様で作り直そうと思います。（7/23）

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
        showTicketIfNeeds(oneDayPassport);
        Ticket twoDayPassport = booth.buyTwoDayPassport(20000).getTicket();
        showTicketIfNeeds(twoDayPassport);
    }
    // 色んな判断基準が考えられるが、今回は値段で判断することにする。ガバガバ条件だが、1万円以上のいチケットはTwoDayPassportとする。
    // done umeyan [いいね] ↑ガバガバ条件というはちゃんとわかってコメントしていることはとても素晴らしいです(^^ by jflute (2024/07/11)
    // done umeyan [読み物課題] オートマティックおうむ返しコメントより背景や理由を by jflute (2024/07/11)
    // https://jflute.hatenadiary.jp/entry/20180625/repeatablecomment
    // 記事の本題はちょっと違いますが、「言い訳コメントも良い訳」のところに通じます。

    // チケットの種類を内部に持つようにする。そしてチケットの種類を取得するメソッドを作成する。（2024/7/23）

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // done umeyan まあ一方で、ForDayが追加されたらもう破綻してしまいますし、TwoDayの価格改定が起きても破綻します by jflute (2024/07/11)
        // どうにかして価格に依存せずに判定できるようにしたいところですね。後回しでも良いのでじっくり考えてみてください。

        // TODO done umeyan [思考課題] "チケットの種類" が現状は大丈夫だけど、もうちょい拡張的な仕様変更が入るようになって... by jflute (2024/07/25)
        // "1day ticket2" (1dayのバージョン2みたいな) とかあったりすると破綻するので、もうちょい種類の表現方法に依存しない実装にしたいところですね。
        // まあでも、恐らくそれを気にして、露骨に equals("2day ticket") は止めて contains() 方式にしたのかなって。
        //
        // "チケットの種類" の文字列が人間に見せる用の文字列なのか？コンピューター上で識別するための文字列なのか？それにも寄ります。
        // いま "2day ticket" っていうように人間が見やすい形式になっていますから画面とかで目で見るための値のように思えてるので、
        // もしその場合、表現というのは簡単に人間の都合で変わったりしますので、表現に依存した判定だと破綻リスクが高いと言えます。
        // (例えば、大文字にして欲しいとか、数字は one, two と英語にして欲しいとか)
        //
        // 色々な方法があるので、1on1で少しフォローします。それまではうーむーって悩んでみてください。
        // [memo] 定数の共有や、ロジックの共有のフォローはした
        // ただ、ご自身で enum (なんて読むのかわからない) の話をされたので乞うご期待。

        // TODO done umeyan [質問] こちらの実装、nightOnlyのtwoDayも含まれてしまいますが、これは意図して含んでますでしょうか？ by jflute (2024/07/25)
        // [memo] これも enum でどうにかなるはず

        // enumで実装した。　by umeda (2024/07/30)
        TicketType ticketType = ticket.getTicketType();
        if (ticketType == TicketType.TWO_DAY) {
            log("two-day passport");
        } else if (ticketType == TicketType.NIGHT_ONLY_TWO_DAY) {
            log("night-only two-day passport");
        } else {
            log("other ticket");
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
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyResult = booth.buyFourDayPassport(30000);
        Ticket fourDayPassport = buyResult.getTicket();
        log(fourDayPassport.getDisplayPrice() + buyResult.getChange()); // should be same as money
        log("入園可能回数：" + fourDayPassport.getAvailableEnterCount());
    }
//    方針　：buyFourDayPassportを作る
    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyResult = booth.buyNightOnlyTwoDayPassport(10000);
        Ticket nightOnlyTwoDayPassport = buyResult.getTicket();
        log(nightOnlyTwoDayPassport.getDisplayPrice() + buyResult.getChange()); // should be same as money
        nightOnlyTwoDayPassport.enterPark(LocalDateTime.of(2024, 7, 23, 16, 0)); // 16時に入場
        log("入園可能回数：" + nightOnlyTwoDayPassport.getAvailableEnterCount());
        nightOnlyTwoDayPassport.enterPark(LocalDateTime.of(2024, 7, 23, 19, 0)); // 19時に入場
        log("入園可能回数：" + nightOnlyTwoDayPassport.getAvailableEnterCount());
    }

    // 18時以降を夜とする。

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
