package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Zombie;

/**
 * The process of barking(吠える) for zombie(ゾンビ).
 * @author umeda-yusuke
 */
public class ZombieBarkingProcess extends BarkingProcess {
    Zombie zombie;

    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);
        this.zombie = zombie;
    }

    @Override
    protected void breatheIn() {
        super.breatheIn();
        zombie.getZombieDiary().countBreatheIn();
    }
}
