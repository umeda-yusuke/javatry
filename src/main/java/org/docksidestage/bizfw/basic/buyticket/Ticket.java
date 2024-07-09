/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    private boolean alreadyIn = false; // true means this ticket is unavailable
    public HashMap<LocalDate, Boolean> availableDate = new HashMap<LocalDate, Boolean>();
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    public void setAbalebleDate(LocalDate date1, LocalDate date2) {
        availableDate.put(date1, true);
        availableDate.put(date2, true);
    }

    public boolean isAbalebleDate(LocalDate date) {
        if (! availableDate.containsKey(date)) {
            return false;
        }
        return availableDate.get(date);
    }

    public boolean enterPark(LocalDate date) {
        if (! isAbalebleDate(date)) {
            return false;
        }
        availableDate.put(date, false);
        return true;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
