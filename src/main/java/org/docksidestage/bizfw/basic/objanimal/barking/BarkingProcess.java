package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The process of barking(吠える) for animal(動物).
 * @author umeda-yusuke
 */
public class BarkingProcess {

    private final Animal animal;
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

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
       animal.downHitPoint();
    }

    private BarkedSound doBark(String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }

}
