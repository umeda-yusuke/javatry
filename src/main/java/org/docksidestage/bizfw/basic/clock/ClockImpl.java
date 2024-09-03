package org.docksidestage.bizfw.basic.clock;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockImpl implements Clock {
    // [ふぉろー] Jstを利用側が意識した方が良い(だろう)サービスならOKだけど、一般的にcurrentDateTime()になるかなと by jflute
    @Override
    public LocalDateTime currentJstDateTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }
}
