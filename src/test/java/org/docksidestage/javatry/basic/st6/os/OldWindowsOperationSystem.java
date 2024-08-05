package org.docksidestage.javatry.basic.st6.os;

/**
 * OldWindowsOperationSystem
 * @author umeda-yusuke
 */
public class OldWindowsOperationSystem extends St6OperationSystem {

    public OldWindowsOperationSystem(String loginId) {
        super("OldWindows", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + getLoginId();
    }
}
