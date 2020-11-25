package com.artemis.covidtestingplatform.models;

import java.util.HashMap;
import java.util.Map;

public enum TimeSlot {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12),
    THIRTEEN(13),
    FOURTEEN(14),
    FIFTEEN(15),
    SIXTEEN(16),
    SEVENTEEN(17),
    EIGHTEEN(18),
    NINETEEN(19),
    TWENTY(20),
    TWENTY_ONE(21),
    TWENTY_TWO(22),
    TWENTY_THREE(23),
    TWENTY_FOUR(24);

    private int value;

    TimeSlot(int value) {
        this.value = value;
    }

    static Map<Integer,TimeSlot> map = new HashMap();
    static {
        for (TimeSlot timeSlot : TimeSlot.values()) {
            map.put(timeSlot.value, timeSlot);
        }
    }
    public static TimeSlot valueOf(int timeSlot) {
        return (TimeSlot) map.get(timeSlot);
    }

    public int getValue() {
        return value;
    }
}
