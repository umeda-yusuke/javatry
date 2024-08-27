package org.docksidestage.bizfw.basic.clock;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockImpl implements Clock {
    @Override
    public LocalDateTime currentJstDateTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }
}
