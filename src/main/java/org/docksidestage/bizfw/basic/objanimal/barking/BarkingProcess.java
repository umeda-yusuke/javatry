package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The process of barking(吠える) for animal(動物).
 * @author umeda-yusuke
 */
public class BarkingProcess {

    // TODO done umeyan Javaの慣習として、static変数(定数)は上で、インスタンス変数はその下ってのがあります by jflute (2024/08/06)
    // TicketBoothを思い出してみてください。(static変数(定数), インスタンス変数, コンストラクター、メソッドたち)
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);
    private final Animal animal;

    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    private void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.downHitPoint();
    }

    private void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        // TODO done umeyan ↓めちゃくちゃ細かいですが、インデントが少しズレてますね by jflute (2024/08/06)
        animal.downHitPoint();
    }

    private BarkedSound doBark(String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    } // TODO done umeyan ↓細かいですが、他のクラスでは見かけない空行なので削除で (一貫性と体裁) by jflute (2024/08/06)
}

// TODO done umeyan [読み物課題] 体裁も細かくレビューするコンセプトはこちらです↓ by jflute (2024/08/06)
// https://twitter.com/jflute/status/1164429226822385664
// 一方で、実務のレビューでは時間もないし意識の統一も難しいから体裁のレビューはほとんどされません。
// だからこそ、それぞれ自分自身で律していかないと、
//「(部屋散らかした状態で)部屋の悪いところ見てもらっていいですか？」
// をレビューワーに押し付けてしまうレビューイーになってしまうと考えていまして。
//
// でもstep6は特に全体的にとても綺麗に整っていてとっても嬉しいです(^^)
// レビューする方もこういうコードはレビューが気持ちいいんですよね。
// レビューワーが気持ちよければ、一杯間違いを見つけてもらえるわけですから、レビューイーも得なのです。