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

import org.docksidestage.unit.PlainTestCase;

// TODO done umeyan ↑なんか、com.sunのMimeUtilityという知らないクラスのquoteメソッドがimportされています(^^ by jflute (2024/07/09)
// そして、unusedの警告が出ていますね。恐らく、test_method_making()のときに間違ってimportしちゃったんじゃないでしょうか？
// できるだけコミット前にIDEの警告が残ってないか？確認する習慣を付けましょう。(でもIntelliJはノイズ的な警告もめっちゃ出るんですね...)
// TODO jflute 1on1にてIDE警告のよもやま話 (2024/07/09)
/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author umeda-yusuke
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething(); // over
        log(sea); // your answer? => over
//        合ってた
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic"); // tic -> mys, in function: mysmys
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys
//        合ってた. functionSomethingでticをmysに置き換えている
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }
    // TODO jflute 1on1にてFunctionインターフェースの名前との関連性のちょいよもやま話 (2024/07/09)
    
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable); // mutable = "mystic"
        if (!land) {
            sea = sea + mutable.getStageName().length(); // 904 + 6 = 910
        }
        log(sea); // your answer? => 910
//        合ってた. mutableのstageNameがmysticになっている。seaやlandは変更されていない
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
        } // 100回hasAnnualPassportがtrueなのでinParkCountが100になる
        ++sea; // 0 + 1 = 1
        sea = inParkCount; // 100
        log(sea); // your answer? =>  100
//        合ってた. hasAnnualPassportがtrueなので100回inParkCountが増える
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // write methods here
    private String replaceAwithB(String str) {
        return str.replace("A", "B");
    }

    private String replaceCwithB(String str) {
        return str.replace("C", "B");
    }

    // TODO done umeyan [いいね]第二引数の名前がとてもわかりやすくて良いです！ by jflute (2024/07/09)
    // いや、時々 (str1, str2) とか、(target, str) とか付けてしまうパターンとかあったりするもので
    // メソッドの引数名って、メソッドをメンテする人のためだけでなく、呼び出す人のためにもわかりやすい変数名にしたいもので。
    private String quote(String str, String quote) {
        return quote + str + quote;
    }

    private boolean isAvailableLogging() {
        return true;
    }

    private void showSea(String str) {
        log(str);
    }
}
