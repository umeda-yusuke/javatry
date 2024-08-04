package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.flyer.Flyable;

public class Bird extends Animal implements Flyable {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Bird.class);

    public Bird() {
    }

    @Override
    public void fly() {
        logger.debug("...Flying now");
        downHitPoint();
    }

    @Override
    protected String getBarkWord() {
        return "piu";
    }

    @Override
    protected void downHitPoint() {
        hitPoint -= 3;
    }
}
