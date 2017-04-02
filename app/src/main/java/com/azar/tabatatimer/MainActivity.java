package com.azar.tabatatimer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView textView;
    private LinearLayout linearLayout;
    private CombinedTimer combinedTimer;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)this.findViewById(R.id.timer_content);
        linearLayout = (LinearLayout)this.findViewById(R.id.linear_layout);
        linearLayout.setBackgroundColor(Color.YELLOW);

        vibrator =  (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        combinedTimer = new CombinedTimer()
                .add(new TextViewTimerAction(0, Color.YELLOW), new TimerDefinition(5000, 1000));

        for (int i = 1; i < 9; ++i) {
            combinedTimer.add(new TextViewTimerAction(i, Color.GREEN), new TimerDefinition(20000, 1000))
                    .add(new TextViewTimerAction(i, Color.RED), new TimerDefinition(10000, 1000));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onTextClick(View v) {
        combinedTimer.start();
    }

    private class TextViewTimerAction implements TimerAction {
        private final int number;
        private final int color;

        public TextViewTimerAction(int number, int color) {
            this.number = number;
            this.color = color;
        }

        @Override
        public void tick(long msecondsLeft) {
            linearLayout.setBackgroundColor(color);
            textView.setText(number + ": " + Math.round(msecondsLeft/ 1000));
        }

        @Override
        public void finish() {
            vibrator.vibrate(500);
        }
    }
}
