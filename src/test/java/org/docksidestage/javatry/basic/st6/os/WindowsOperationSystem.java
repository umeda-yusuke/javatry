package org.docksidestage.javatry.basic.st6.os;

/**
 * WindowsOperationSystem
 * @author umeda-yusuke
 */
public class WindowsOperationSystem extends St6OperationSystem {

    public WindowsOperationSystem(String loginId) {
        super("Windows", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
