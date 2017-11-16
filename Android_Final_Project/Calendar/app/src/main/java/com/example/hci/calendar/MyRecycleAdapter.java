package com.example.hci.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    private List<BeanClassForListView> moviesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView clock;
        ImageView lolipop;
        ImageView banner1;
        TextView title;
        TextView time;
        TextView task;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            task = (TextView) view.findViewById(R.id.task);
            clock = (ImageView)view.findViewById(R.id.clock);
            banner1 = (ImageView)view.findViewById(R.id.banar1);
            lolipop = (ImageView) view.findViewById(R.id.lolipop);
        }
    }


    public MyRecycleAdapter(List<BeanClassForListView> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeanClassForListView movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.task.setText(movie.getTask());
        holder.time.setText(movie.getTime());
        holder.banner1.setImageResource(movie.getProfile_image());
        holder.lolipop.setImageResource(movie.getLolipop_image());
        holder.clock.setImageResource(movie.getClock_image());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
