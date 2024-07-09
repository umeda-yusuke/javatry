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
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1);
        land = bonvo.getMonthValue(); // 10
        land--; // 9
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land)); // 9.4 + 9 = 18.4
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
//        合ってた。BigDecimalの足し算は、BigDecimal型の値を引数に取るaddメソッドを使う。
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) { // dohotel = true, dstore = 1.1, piari = 1 →　true
            bonvo = sea; // 127
            land = (short) bonvo; // 127
            bonvo = piari; // 1
            sea = (byte) land; // 127
            if (amba == 2.3D) {
                sea = (byte) amba; // 2.3 → 2(byte型にすると小数点以下が切り捨てられる)
            }
        }
        if ((int) dstore > piari) { // 1 > 1 → false
            sea = 0;
        }
        log(sea); // your answer? => 2
//        合ってた。byte型にdouble型の値を代入すると、小数点以下が切り捨てられる。
        
        // TODO done umeyan [読み物課題]プリミティブ型とラッパー型、こちらぜひ読んでみて理解を深めてみてください by jflute (2024/07/09)
        // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#primitivewrapper
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hanger
//        合ってた。St3ImmutableStageクラスのインスタンスを生成し、そのインスタンスのgetStageNameメソッドを呼び出している。
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
