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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author umeda-yusuke
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
//        合ってた。seaは904より大きくないのでelseに入る。結果は7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => ７
//        合ってた。seaは904より大きくないのでifに入らず、１つ目のelseifに入る。以降のelseifは無視される。結果は7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) { // true || false = true ここに入る
            sea = 8;
            if (!land) {  // !false = true
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { // sea=8, land=true
            sea--; // 8-1=7
        }
        if (land) { // land=true
            sea = 10;
        }
        log(sea); // your answer? => 10
//        合ってた。めちゃ読みずらかった。
        // TODO umeyan [いいね]一生懸命読みづらく書いたので褒め言葉、やったー(^^ by jflute (2024/07/01)

        // TODO jflute 1on1でコードリーディングのちょいコツの話をする予定 (2024/07/01)
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // [broadway, dockside, hangar, magiclamp]
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? =>　dockside
//      合ってた。stageList.get(i)でi=1の時のstageをseaに代入している。結果はprepareStageList()の1番目の要素であるdockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // [broadway, dockside, hangar, magiclamp]
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
//      合ってた。stageListの要素をseaに代入し続けている。結果は最後に代入した要素。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // [broadway, dockside, hangar, magiclamp]
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
//      合ってた。"br"で始まる要素はcontinueでスキップ。"ga"を含む要素があるとbreakで終了。breakされる前の要素がhangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList(); // [broadway, dockside, hangar, magiclamp]
        StringBuilder sb = new StringBuilder(); // 初期値は空文字列
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
//      合ってた。sbの長さが０以上になったら、ループが終わるので、要素に"i"が含まれる最初の要素がseaに代入される。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> aList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                aList.add(stage);
            }
        }
        for (String aStage : aList) {
            log(aStage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList(); // [broadway, dockside, hangar, magiclamp]
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.startsWith("br")) {
                return;
            }
            if (stage.contains("ga")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // should be same as before-fix
//      forEach内ではfinalな変数しか使えないらしい（これよく分からない）。なので、seaをsbに変更して、最後にsb.toString()でseaに代入するように変更。
//      参考にした→　https://qiita.com/turn-take/items/ac6971ef8f0e35ef51c9
//      for内の処理は、brから始まらず、gaを含む最初の要素をseaに代入したら良いと解釈したので、breakを使わずにそれができるように変更。
        // TODO umeyan [いいね] sbのlength()でskipさせて実質的なbreakにしてるの良い発想ですね by jflute (2024/07/01)
        
        // TODO umeyan 修行++: もし、stageList の中に「"ga"を含むもの」が一つもなかった場合、結果は同じになるでしょうか？ by jflute (2024/07/01)
        // prepareStageList()で戻って来るリストの中身が変動することを想定して実行結果が同じになるか？をちょっと考えてみましょう。
        
        // TODO jflute 1on1にてコールバック内におけるfinal変数限定の参照について補足予定 (2024/07/01)
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * forEachを使って、prepareStageListからcを含む要素だけを抽出した配列を作れ
     * 抽出した配列をログに表示せよ
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        List<String> stageList = prepareStageList();
        List<String> cList = new ArrayList<>();
        stageList.forEach(stage -> {
            if (stage.contains("c")) {
                cList.add(stage);
            }
        });
        log(cList);
        
        // TODO umeyan Javaだと(会話上)「配列」と「リスト」というのはけっこう厳密に区別をする傾向にあります。 by jflute (2024/07/01)
        // なので、"配列を作れ" と言われたら、List<>ではなく、String[]を最終成果物にする印象です。
        // とはいえ中間成果物でList<>を使っちゃいけないわけではないので、最後にcListから配列を作り出すとしっくり来るかもですね。
        //
        // まあでも、これは「Javaならでは」な気もします。配列とリストを一つのもので表現してる言語も多いと思うので。
        // プリミティブ型とオブジェクト型と両方が存在してるのも似たような話で、
        // Javaはオブジェクトメインの文法を用意しておきながらも、C言語っぽいプリミティブな文法も用意してるという感じですね。
        // オブジェクトはメモリを食いますから、もっとコンピューターネイティヴなプログラミングもできるように残してると。
        // 組み込み系プログラミングとかで何かの機械の中でJavaを使うとかも想定して。
        // でもWebサービス開発とかだと、サーバーにメモリがたんまりあるのであんまり気にしないですが(^^
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
