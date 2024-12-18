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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author yusuke umeda
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? => hangerbroadway
    }

    // あってた。
    // thrower.land();でIllegalStateExceptionが発生するのでcatch節が実行され、"hangar"が追加される。
    // その後、finally節が実行され、"broadway"が追加される。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => "oneman at showbase"
        // 1on1で一緒にやった by jflute
    }

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => St7BasicExceptionThrowerのonemanメソッドの40行目
    }
    // あってた。

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
    }
    // あってた。
    // IllegalStateExceptionはRuntimeExceptionを継承しているので、expはRuntimeExceptionのインスタンスでもある。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
        // [ふぉろー] 1on1で一緒にやった by jflute
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
    }
    // あってた。
    // IllegalStateException　←　RuntimeException　←　Exception　←　Throwable

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
    }
    // あってた。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
    }
    // あってた。
    // Exception ← Throwableであり、ExceptionはThrowableを継承しているが、ThrowableはExceptionを継承していない。

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => land, toLowerCaseメソッドの2563行目
    }
    // あってる？145行目と書いた方がよかった？
    // [ふぉろー] そもそも toLowerCase() が呼べてない、呼ぶ前に land. で NullPointer

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => piari, 160行目
    }
    // あってた。

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;

            int landLength = land.length();
            int piariLength = piari.length();
            int sum = landLength + piariLength;
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
    }
    // こういうこと？？
    // [ふぉろー] yes, good. 一方で、Java14以降は例外メッセージでわかるようになっている話もした

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        try {
            String canonicalPath = new File(".").getCanonicalPath();
            log(canonicalPath);
        } catch (IOException e) {
            // TODO umeyan 例外メッセージが重複して表示されるので... by jflute (2024/09/19)
            //  e.g. log("I/O error occurred", e);
            log("I/O error occurred: " + e.getMessage());
            log(e);
        }
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? =>  Failed to call the second help method: symbol=-1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? => IllegalStateException
        }
    }
    // 間違ってる
    // throwCauseThirdLevelメソッドでNumberFormatExceptionが発生しているので、それが原因となっている。
    // symbolは最初１で、その後に2回-1されているので、-1になっている。
    // [ふぉろー] メッセージのところが third でした。
    // でも、例外クラスは合ってるから、階層自体は頭の中でちゃんとわかっていたかなと。


    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe;
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;
            symbol--;
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");
        }
    }
    
    // [ふぉろー] 例外は例外を持つことができる、何階層もつなげて保持できる
    // jflute 1on1にて、なんで例外は例外を持つことができるのか？(持つ必要があるのか？)を聞きます (2024/09/19)
    // この図を使ってとっくり説明 (スーパーカー)
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/exception.html

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            //　スーパーカーを作ろうとして、必要な車のハンドルを取得しようとしたが、車のハンドルID３であるspecText: \(^_^)/ではハンドルを作ることができないため、エラーが発生した。
            //
            //
            // _/_/_/_/_/_/_/_/_/_/
            // done umeyan [いいね] 簡潔にありがとうございます。全体のストーリーに触れていてわかりやすいです。 by jflute (2024/10/14)
            //
            // 以前1on1で例外の翻訳の話をしましたが、この時点のスーパーカーは翻訳が全く無いので、
            // 例外メッセージだけだと末端の事情しかわからなくて事象の全体像を把握するのがとても難しいです。
            // なのでソースコードを読まないとわからないってことなわけですし、
            // 実際のアプリではIDとかの出どころはリクエストパラメーターとかDBとかになりますので、
            // 翻訳例外がないとデバッグ情報のロスが生まれて原因を掴むのがほぼ不可能になるケースもよくあります。
            //
            // なので、例外メッセージだけで全体像を把握できるように、情報ロスが発生しないように、
            // 例外の翻訳を入れてみようというのが次のエクササイズですね。
        }
    }

    // TODO umeyan ここからまだ途中 by jflute (2024/11/01)
    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // TODO umeyan ネストの例外メッセージを引き継いでいるというのは良いことですね by jflute (2024/11/07)
            // ただ、これだとネストの例外のスタックトレースが引き継がれていないので、デバッグ情報としてはロスが発生しています。
            // ここは、test_exception_cause_basic() のエクササイズを思い出してみましょう。
            // ネストの例外の例外メッセージとスタックトレース、翻訳例外の例外メッセージとスタックトレース、
            // すべてが (try/catchしてる箇所での) ログで表示できるようにしましょう。
            throw new St7ConstructorChallengeException(e.getMessage());
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        // Exceptionは準正常系であり、予期したエラー。例外とかともいう
        // Errorは異常系であり、予期しないエラー。
        //
        //
        // _/_/_/_/_/_/_/_/_/_/
        // [1on1でのふぉろー] ↑おおよそGood, step7の最初の方で話したこと理解されていますね！
        // 予期の解釈は若干曖昧ではありますが、準正常というニュアンスは素晴らしいです。
        // throwする瞬間はあくまで準正常系、catchして判断するときにエラーと解釈するかそのまま準正常か決められる。
    }
}
