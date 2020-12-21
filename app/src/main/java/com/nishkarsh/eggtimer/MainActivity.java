package com.nishkarsh.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;
    Boolean temp=false;
    CountDownTimer countDownTimer;

    public void reset(){
        seekBar.setEnabled(true);
        button.setText("GO !");
        seekBar.setProgress(30);
        textView.setText("0:30");
        temp=false;
        countDownTimer.cancel();
    }

    public void onClick(View view) {

        if (temp) {
            reset();
        }
        else {
            temp=true;
            seekBar.setEnabled(false);
            button.setText("STOP !");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    timer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);  // Horn sound
                    mediaPlayer.start();
                    reset();
                }
            }.start();
        }
    }

    public void timer(int i){
        int min=i/60;
        int sec=i%60;
        String seconds=Integer.toString(sec);
        if(sec/10==0){
            seconds="0"+sec;
        }
        textView.setText(Integer.toString(min)+":"+seconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                timer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
