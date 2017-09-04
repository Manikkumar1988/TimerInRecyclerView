package com.mani.rc;/**
 * Created by mani on 03/09/17.
 */

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

  private Context context;
  private List<CustomTimer> list;
  private OnItemClickListener onItemClickListener;

  public RecyclerViewAdapter(Context context, List<CustomTimer> list,
      OnItemClickListener onItemClickListener) {
    this.context = context;
    this.list = list;
    this.onItemClickListener = onItemClickListener;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    // Todo Butterknife bindings

    CountDownTimer countDownTimer;
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(final CustomTimer model, final OnItemClickListener listener) {
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          listener.onItemClick(getLayoutPosition());
        }
      });
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
    CustomTimer item = list.get(position);

    //Todo: Setup viewholder for item 
    holder.bind(item, onItemClickListener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }
}
