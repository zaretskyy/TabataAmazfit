package com.azar.tabatatimer;

import android.os.CountDownTimer;


public class CombinedTimer {
    private SequentialCountDownTimer prevois;
    private volatile boolean kill;
    private SequentialCountDownTimer first;

    public CombinedTimer add(TimerAction action, TimerDefinition definition) {
        SequentialCountDownTimer countDownTimer = new SequentialCountDownTimer(action, definition);
        if(prevois != null) {
            prevois.setNext(countDownTimer);
            prevois = countDownTimer;
        } else {
            prevois = countDownTimer;
            first = countDownTimer;
        }

        return this;
    }

    public void start() {
        if(first != null) {
            first.start();
        }
    }

    private class SequentialCountDownTimer extends CountDownTimer {
        public void setNext(CountDownTimer next) {
            this.next = next;
        }

        private CountDownTimer next;
        private final TimerAction action;

        public SequentialCountDownTimer(TimerAction action, TimerDefinition definition) {
            super(definition.getTotalMSec(), definition.getStepMSec());
            this.action = action;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(kill) {
                cancel();
            }
            action.tick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            if(kill) {
                cancel();
            }

            action.finish();
            if(next != null) {
                next.start();
            }
        }
    }
}
