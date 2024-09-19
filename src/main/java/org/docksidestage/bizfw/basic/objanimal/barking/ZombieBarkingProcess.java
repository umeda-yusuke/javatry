package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Zombie;

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
