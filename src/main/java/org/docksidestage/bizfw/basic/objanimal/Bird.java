package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.flyer.Flyable;

// TODO umeyan javadocのインデントがちょっとへんです by jflute (2024/08/06)
/**
    * The object for bird(鳥).
    * @author umeda-yusuke
 */
public class Bird extends Animal implements Flyable {

    // TODO umeyan [いいね] Slf4jのLogger定義の仕方、合っています by jflute (2024/08/06)
    // TODO umeyan FQCNではなく、import文を使って Logger, LoggerFactory で書けるようにしましょう by jflute (2024/08/06)
    // (FQCNはどうしてもって時でない限りは通常のコード上では利用しないです。やはり見づらくなってしまうので)
    // (BarkingProcessではFQCNを使ってないのにここでは使っている、というのもコードの一貫性に通じる話になりますね)
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Bird.class);

    public Bird() {
    }

    @Override
    public void fly() {
        logger.debug("...Flying now");
        downHitPoint();
    }

    @Override
    public String getBarkWord() {
        return "piu";
    }

    @Override
    public void downHitPoint() {
        hitPoint -= 3;
    }
}
