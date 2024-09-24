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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for animal(動物).
 * @author jflute
 * @author umeda-yusuke
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP

    // done umeyan [いいね] this突っ込んでるのオシャレ（＾＾ by jflute (2024/08/06)
    // これが正解かどうかは置いておいて、インスタンスという概念を理解している証拠ですね！
    protected final BarkingProcess barkingProcess = createBarkingProcess();
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // done umeyan [いいね] すっきりしてしっかり委譲(delegate)されていますね by jflute (2024/08/06)
    // done jflute 委譲とかdelegateとかの言葉の意味についてフォロー (2024/08/06)
    // (一応、転送と委譲の話も、すごく軽く触れた。とりあえず移譲でいいです)
    // TODO umeyan publicではなくprotectedで良い by jflute (2024/09/24)
    // 呼び出している人は自分しかいないし、オーバーライドはprotectedで十分。
    // (無駄にpublicにはできるだけしないほうが良い)
    protected BarkingProcess createBarkingProcess() {
        return new BarkingProcess(this);
    }

    public BarkedSound bark() {
        return barkingProcess.bark();
    }

    // [ふぉろー] publicに変更されているけど、まあここは業務的に問題はなさそうではる by jflute (2024/09/24)
    // ただ、publicにするときに「ああぼくはpublicにしちゃってるぞ心」を持っていればOK。
    // TODO umeyan 修行++: 一方で、けっこうシンプルな方法でprotectedに戻すことはできそう by jflute (2024/09/24)
    // (protectedに戻しつつ、BarkingProcessも今と同じように振る舞うことはできる: 特殊な文法は使わない)
    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // [ふぉろー] publicに変更されているけど、こっちは状態を変更するようなわりとオブジェクトにとってクリティカルな振る舞いなので...
    // 極力publicである必要がないなら、publicにはしたくないところ by jflute (2024/09/24)
    // TODO umeyan 修行++: 一方で、まあまあ高度な方法でprotectedに戻すことはできそう by jflute (2024/09/24)
    // hint1: step8が関連するかな!? (少しstep6のエッセンスも入る)
    // hint2: BarkingProcessをAnimal依存ではなく、汎用的な吠えるLogicにするとしたら？
    //         => 手段が同じなので、自然と(同時に)解決する
    public void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
