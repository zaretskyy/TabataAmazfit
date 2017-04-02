package com.azar.tabatatimer;

public interface TimerAction {
    void tick(long msecondsLeft);
    void finish();
}
