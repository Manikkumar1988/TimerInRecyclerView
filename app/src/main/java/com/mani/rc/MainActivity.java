package com.mani.rc;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  TextView holder;
  CountDownTimer countDownTimer;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    holder = (TextView) findViewById(R.id.timestamp);


    countDownTimer = new CustomTimer(10000, 500);
    final CustomRunnable customRunnable = new CustomRunnable();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        //countDownTimer.start();

        handler.removeCallbacks(customRunnable);
        customRunnable.millisUntilFinished = 4000; //Current time - received time
        handler.postDelayed(customRunnable, 100);
      }
    });
  }

  @Override protected void onPause() {
    super.onPause();
    //countDownTimer.cancel();
    handler.removeCallbacksAndMessages(null);
    holder = null;
  }


  private Handler handler = new Handler();

  public class CustomRunnable implements Runnable {

    public long millisUntilFinished = 40000;

    @Override
    public void run() {
      /* do what you need to do */
      long seconds = millisUntilFinished / 1000;
      long minutes = seconds / 60;
      long hours = minutes / 60;
      long days = hours / 24;
      String time = days+" "+"days" +" :" +hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
      holder.setText(time);

      millisUntilFinished -= 1000;

      Log.d("DEV123",time);

      /* and here comes the "trick" */
      handler.postDelayed(this, 1000);
    }

  };

  public class CustomTimer extends CountDownTimer{

    public CustomTimer(long millisInFuture, long countDownInterval) {
      super(millisInFuture, countDownInterval);
      Log.d("DEV123","Creating new object");
    }

    public void onTick(long millisUntilFinished) {
      long seconds = millisUntilFinished / 1000;
      long minutes = seconds / 60;
      long hours = minutes / 60;
      long days = hours / 24;
      String time = days+" "+"days" +" :" +hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
      holder.setText(time);

      Log.d("DEV123",time);
    }

    public void onFinish() {
      holder.setText("Time up!");
    }
  }
}
