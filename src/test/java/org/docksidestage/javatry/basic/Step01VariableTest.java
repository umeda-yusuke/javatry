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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

// done umeyan これは伝えてなかったのでしょうがないのですが、今伝えさせてください by jflute (2024/06/30)
// 一応、javatryの実装ポリシーとして、クラスのJavaDocのauthorの your_name_here のところを自分の名前に変えるようにお願いします。
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author umeda-yusuke
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai
//        piariにnullという文字列が入るか、何も入っていないか迷った。合ってた
        // done umeyan 些細な話ですが言語によってこういうところ違ったりするものなんですよね by jflute (2024/06/30)
        // ですよね。そんな気がしたので、一か八かで当てました。
        // done jflute 1on1で軽くフォロー予定 (Java以外の言語の話やnull事件) (2024/06/30)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
//        合ってた。変数seaに変数landの値が代入されているため、seaの値はlandの値になる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
//        合ってた。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);  // +1
        BigDecimal land = new BigDecimal(415); // +1
        sea = land;
        sea = land.add(new BigDecimal(1)); // +1(1), +1(416)
        sea.add(new BigDecimal(1)); // +1(1), +1(417)
        log(sea); // your answer? => 416
//        this + augendが返される。BigDecimalはimmutable（内部で情報が変わらない）なので、seaには416が代入されるが、sea.add(new BigDecimal(1));はseaには影響しない。
//        合ってた。
     // done jflute 1on1で軽くフォロー予定 (immutable話、javadocやコードリーディングを交えて) (2024/06/30)
        // Returns a BigDecimal whose value is (this + augend),
        // and whose scale is max(this.scale(), augend.scale()).
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => ””
//        空文字列と思ったが、nullだった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => null
//        nullと思ったが、0だった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => 0
//        0と思ったが、nullだった。問題作成者の思う壺になってて腹立つ。
//        intとIntegerの違いを理解していなかった。intはプリミティブ型で、初期値は0。Integerはクラス型で、初期値はnull。
        // done umeyan 丁寧に学んで頂きありあとうございます。プリミティブ型の話はstep3でもやります by jflute (2024/06/30)
//        そうなんですね。プリミティブ型の話はstep3でもやるんですね。楽しみです。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband｜1|null|magician
//        合ってた。スコープ範囲の問題ですね。グローバル変数としてinstanceBroadwayとinstanceDocksideはhelpInstanceVariableViaMethod内で扱われている。
//        そのため、helpInstanceVariableViaMethod内での変更が反映されている。instanceMagiclampは引数として渡されているため、helpInstanceVariableViaMethod内での変更が反映されていない。
        // done umeyan さすが問題なしですね。プログラミング初心者で間違い多い箇所なんですよ by jflute (2024/06/30)
        // 変数名が同じだと同じもの(箱/変数)だと思ってしまいがちで、でも言う通り「スコープ範囲」の問題ですよね。
        // 違うスコープの変数が名前かぶりした場合、基本的には近い方(狭い方)が優先されます。(他の言語でもだいたい同じかな!?)
        // ちなみに、ここではまだ「クラス」という概念を意識していないのですが、そのグローバル変数は「インスタンス変数」と呼びます。
        // Step01VariableTestクラスの1インスタンスに属する変数ということでインスタンス変数です。
        // done jflute 1on1で軽くフォロー予定 (インスタンスとは？の問い) (2024/06/30)
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
//        合ってた。
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
//        合ってた。今回seaはStringBuilder型であり、appendでretuen thisをしているため、内部で変更がされている。
//        ただ感覚でわかったのでちゃんと解説欲しい。
        // done jflute 1on1でフォロー予定 (mutableの引数、変数とインスタンス) (2024/06/30)
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land); // このseaは、testの方のseaが指し占めている同じStringBuilderインスタンスを指している
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
//        合ってた。
//        helpMethodArgumentVariable内でseaに新しいStringBuilderが代入されているが、そのスコープ範囲はhelpMethodArgumentVariable内で終わっているため、外部には影響がない。
        // done umeyan パーフェクトです！(インスタンスが違えば別物だし、そのnewインスタンスはメソッド内で消えるし) by jflute (2024/06/30)

        // [memo] by jflute (2024/07/04)
        // log(sea);
        // // seaとappendedは同じインスタンスを指し占めている
        // StringBuilder appended = sea.append("aaaa");
        // // メソッドチェーンがしたいという人間の欲望のためにreturnしてる
        // sea.append("999").append("888").append(1111);
        // log(sea);
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    private int piari;
    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        // done umeyan インスタンス変数を定義して欲しいという話なので、piari変数の定義場所をinstanceDocksideと似たような場所にして欲しいということです by jflute (2024/06/30)
//        なるほどです。piari変数の定義場所を変更しました。
        // doen umeyan あまり本質的ではないフォローですが、log()メソッドの引数定義とjavadocを読んでみてください by jflute (2024/06/30)
        // (可変長引数として複数の引数を指定できて、それら引数をカンマで連結してログ出力するようになっています)
        log(sea, land, piari);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * ローカル変数、名前はnum, 型はInteger, 初期値は 10
     * 型をStringに変換して、それぞれの型をlog()で表示
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
        Integer num = 10;
        String str = num.toString();
        log(num, num.getClass().getName());
        log(str, str.getClass().getName());
        // done umeyan いいですね！型を表示しなさいってところが面白いです(^^ by jflute (2024/06/30)
//        intとIntegerの違いとして、文字列に変換するメソッドの有無があるということを知ったので、それを使ってみました。
    }
}
