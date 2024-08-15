package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.flyer.Flyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// done umeyan javadocのインデントがちょっとへんです by jflute (2024/08/06)
/**
 *  The object for bird(鳥).
 *  It implements Flyable interface.
 *  @author umeda-yusuke
 */
public class Bird extends Animal implements Flyable {

    // done umeyan [いいね] Slf4jのLogger定義の仕方、合っています by jflute (2024/08/06)
    // done umeyan FQCNではなく、import文を使って Logger, LoggerFactory で書けるようにしましょう by jflute (2024/08/06)
    // (FQCNはどうしてもって時でない限りは通常のコード上では利用しないです。やはり見づらくなってしまうので)
    // (BarkingProcessではFQCNを使ってないのにここでは使っている、というのもコードの一貫性に通じる話になりますね)
    private static final Logger logger = LoggerFactory.getLogger(Bird.class);

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
