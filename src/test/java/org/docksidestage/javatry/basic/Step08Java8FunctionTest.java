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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author umeda-yusuke
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // Javaよもやま:
    // java8, java11, java17, java21 だけが LTS (長いサポート) されているもの
    // Javaを作ってる人はOracle社 (昔はSun MicroSystemsでOracleが買収)
    // ちなみに、元々MySQLを作ってた会社MySQL AB社をSun Microsystemsが買収

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() { // 無名クラスと呼ぶ
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });

        // ここからJava8
        // Lambda式が書ける条件としては、interfaceのメソッドが一個であること
        // [memo] 言語の特徴の違いの話も (省略の話)
        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));

        // your answer? => yes

        // cannot reassign because it is used at callback process
        //title = "wave";
    }
    // St8BasicConsumerクラスのacceptメソッドで「log(stage + ": " + title)」が呼び出されているため、他のコールバック処理でも同じ文字列が出力される。

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hangar, lost river
    }
    // 合ってたけど、よくわからない。解説欲しい
    // 1on1にてフォローdone by jflute

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
    }
    // 合ってたけど、numberに7が入っていく流れがわからない。解説欲しい
    // 1on1フォローdone by jflute (2024/11/01)
    // コールバックは厳密には難しい、でも定型的な書き方で提供して意識させずに書いてもらうってのが多い by jflute (2024/11/01)
    // (DBFluteのConditionBeanの例で説明)

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        // done umeyan 後でやってもらえればと by jflute (2024/11/01)
        helpCallbackSupplier(() -> { // sea
            return "broadway";
        });
        
        // [よもやま] 括弧のありなしで、フォーマッターのすれ違いを微調整できたりする by jflute (2024/11/05)
        helpCallbackSupplier(() -> "dockside"); // land

        helpCallbackSupplier(() -> {
            return "hangar";
        }); // piari
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // JavaのOptionalは単なるクラスであって文法ではない。
    // なので、Optionalの変数にnullは入ってしまうけど、世界的なマナーで入れない！でなんとか成り立ってる。
    // done jflute 次回1on1ここから (2024/11/01)
    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        // your answer? => yes
    }
    // 合ってた。isPresent()がnullチェックをしている
    // selectMemberは、oldselectMemberをOptionalでラップしている

    // [よもやま] Optionalの原始的なコンセプト。
    // Optionalの導入のタイミングの話。(Lambda式と一緒というのがポイント)
    // Javaだと、Optionalの利用は若干中途半端にならざるを得ない。(両方のパターンありえる)

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });
        // your answer? => yes
    }
    // 合ってたけど、menberにoptMemberが入っていく流れがわからない。解説欲しい

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal();
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason();
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.getWithdrawalId()) // ID here
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        log(amba); // your answer? => *no reason: someone was not present
        log(miraco); // your answer? => -1
    }

    // getWithdrawalIdである事を見落とした。12である

    // [よもやま] Optional@get()問題
    // isPresent()せずにget()するパターンの是非が議論されることが多かった。
    // もちろん、ないかもしれないのに問答無用get()したらOUTなのは当然だけど...
    // 例えば、引数で1を入れた場合、業務的には絶対に存在すると言い切れるような場面...
    // (つまり、戻り値のありなしが引数に寄って変わるような場面)
    //
    // このとき、ifPresent()するか？ => 万が一なかった場合(バグとか勘違い)、素通りする
    // もしくは問答無用get()するか？ => 万が一なかった場合(バグとか勘違い)、しょぼい例外
    // もしくはorElseThrow(...new)するか？ => 万が一なかった場合(バグとか勘違い)、ちゃんとした例外
    //    (ちゃんとした例外というのは、例外メッセージにデバッグ情報がしっかり入っている例外のこと)
    //    (しょぼい例外をthrowするのであれば、それはget()するのと情報が何も変わらない)
    //    (あと費用対効果の問題...)
    //
    // その後、Java10から引数のない orElseThrow() というメソッドが追加された。
    // 実装の中身は get() と100%同じ。(メソッド名が違うだけ)
    // これが問答無用get()の代わり？ (ここはjfluteの感想)
    //
    // (Kotlin)
    // int? Entity?で受け取ったときで、でも入れた引数からすると業務的には絶対に存在するって場合、
    // Entity? select(...) {
    // }
    // var entity = select(1); // 1は絶対存在する (varの型は?になっている)
    // var memberName = entity?.memberName // Javaだと => orElse(null)
    // var memberName = entity!!.memberName // Javaだと => 恐らくget()に近い？
    //
    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
    }

    // あってた

    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => [broadway, dockside]

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => [broadway, dockside]
    }
    // あってた

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())
                .flatMap(mb -> mb.getPurchaseList().stream())
                .filter(pur -> pur.getPurchaseId() > 100)
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct()
                .sum();
        log(sea); // your answer? => 100 + 200  + 300 = 600
    }

    // あってた.
    // これはwithdrawalがnullじゃなくて、purchaseIdが100より大きいものを抽出して、purchasePriceを合計する処理。
    // distinct()は重複を削除する処理

    // *Stream API will return at Step12 again, it's worth the wait!
}
