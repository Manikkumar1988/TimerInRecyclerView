package com.mani.rc;/**
 * Created by mani on 03/09/17.
 */

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import butterknife.BindView;
import java.util.List;

import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

  private Context context;
  private List<CustomTimer> list;
  private OnItemClickListener onItemClickListener;
  private Handler handler = new Handler();

  public RecyclerViewAdapter(Context context, List<CustomTimer> list,
      OnItemClickListener onItemClickListener) {
    this.context = context;
    this.list = list;
    this.onItemClickListener = onItemClickListener;
  }

  public void clearAll() {
    handler.removeCallbacksAndMessages(null);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    // Todo Butterknife bindings
    @BindView(R.id.timestamp) TextView timeStamp;
    CustomRunnable customRunnable;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      customRunnable = new CustomRunnable();
    }

    public void bind(final CustomTimer model, final OnItemClickListener listener) {
      /*itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          listener.onItemClick(getLayoutPosition());
        }
      });*/

      handler.removeCallbacks(customRunnable);
      customRunnable.holder = timeStamp;
      customRunnable.millisUntilFinished = 1000 * getAdapterPosition(); //Current time - received time
      handler.postDelayed(customRunnable, 100);

    }
  }

  public class CustomRunnable implements Runnable {

    public long millisUntilFinished = 40000;
    public TextView holder;

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

  }


  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.recycler_item, parent, false);
    ButterKnife.bind(this, view);

    ViewHolder viewHolder = new ViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    //CustomTimer item = list.get(position);

    //Todo: Setup viewholder for item 
    holder.bind(null, onItemClickListener);
  }

  @Override public int getItemCount() {
    return 100;//list.size();
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }
}
