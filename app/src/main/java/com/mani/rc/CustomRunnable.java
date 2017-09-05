package com.mani.rc;

import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomRunnable implements Runnable {

  public long millisUntilFinished = 40000;
  public TextView holder;
  Handler handler;
  ImageView imageView;

  public CustomRunnable(Handler handler,TextView holder,long millisUntilFinished,ImageView imageView) {
    this.handler = handler;
    this.holder = holder;
    this.millisUntilFinished = millisUntilFinished;
    this.imageView = imageView;
  }

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
    imageView.setX(imageView.getX()+seconds);
      /* and here comes the "trick" */
    handler.postDelayed(this, 1000);
  }

}