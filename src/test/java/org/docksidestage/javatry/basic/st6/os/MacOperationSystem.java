package org.docksidestage.javatry.basic.st6.os;

/**
 * MacOperationSystem
 * @author umeda-yusuke
 */
public class MacOperationSystem extends St6OperationSystem {

    public MacOperationSystem(String loginId) {
        super("Mac", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
