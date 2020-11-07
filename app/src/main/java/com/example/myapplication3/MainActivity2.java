package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
private static final long START_TIME_IN_MILLIS=600000;
private TextView tvcountdown;
private Button btnstartpause;
    private Button btnreset;
    private CountDownTimer Countdowntimer;
    private boolean timerRunning;
    private long timerleftinmillis=START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvcountdown=(TextView)findViewById(R.id.tv_countdown);
       btnstartpause=(Button)findViewById(R.id.btn_start_pause) ;
       btnreset=(Button)findViewById(R.id.btn_reset) ;
       btnstartpause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
if (timerRunning) {
    Pause();
}
else {
    Start();

}
           }
       });
       btnreset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
Reset();
           }
       });
    }

    private void Reset() {
        timerleftinmillis =START_TIME_IN_MILLIS;
        UpdateCountDownText();
        btnreset.setVisibility(View.INVISIBLE);
        btnstartpause.setVisibility(View.VISIBLE);
    }

    private void Start() {
        Countdowntimer   = new CountDownTimer(timerleftinmillis, 1000) {
            @Override
            public void onTick(long millisUntilfinished) {
                timerleftinmillis = millisUntilfinished;
                UpdateCountDownText();

            }

            @Override
            public void onFinish() {
timerRunning=false;
btnstartpause.setText("Start");
btnstartpause.setVisibility(View.INVISIBLE);
btnreset.setVisibility(View.VISIBLE);
            }

            public void onFinished() {
            }
        }.start();
timerRunning=true;
btnstartpause.setText("pause");
btnreset.setVisibility(View.INVISIBLE);
    }

    private void UpdateCountDownText() {
        int minutes =(int) (timerleftinmillis /1000) / 60;
        int seconds =(int) (timerleftinmillis /1000) % 60;
String timeleftformatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
   tvcountdown.setText(timeleftformatted);
    }

    private void Pause() {
        Countdowntimer.cancel();
        timerRunning=false;
        btnstartpause.setText("Start");
        btnreset.setVisibility(View.VISIBLE);
    }


}