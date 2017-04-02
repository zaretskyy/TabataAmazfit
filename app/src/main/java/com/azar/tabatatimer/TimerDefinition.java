package com.azar.tabatatimer;

public class TimerDefinition {
    private final long totalMSec;
    private final long stepMSec;

    public long getTotalMSec() {
        return totalMSec;
    }

    public long getStepMSec() {
        return stepMSec;
    }

    public TimerDefinition(long totalMSec, long stepMSec) {
        this.totalMSec = totalMSec;
        this.stepMSec = stepMSec;
    }
}
