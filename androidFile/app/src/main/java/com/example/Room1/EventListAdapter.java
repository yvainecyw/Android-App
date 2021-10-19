package com.example.Room1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuafeia.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {
    private final LayoutInflater mInflater;
    private List<Event> mEvent;

    public EventListAdapter(Context context){mInflater = LayoutInflater.from(context);}

    @NotNull
    @Override
    public EventViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.calendar_list_item,parent,false);
        return new EventViewHolder(itemView);
    }

    public void setEvent(List<Event> events){
        mEvent = events;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if(mEvent!=null){
            Event current = mEvent.get(position);
            holder.list_time.setText(current.getTime());
            holder.list_task.setText(current.getTask());
            holder.list_type.setText(current.getType());
        }
        else{
            holder.list_time.setText("0");
            holder.list_task.setText("0");
            holder.list_type.setText("0");
        }
    }

    @Override
    public int getItemCount() {
        if (mEvent != null)
            return mEvent.size();
        else return 0;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView list_time;
        private final TextView list_task;
        private final TextView list_type;

        private EventViewHolder(View itemView) {
            super(itemView);
            list_task = itemView.findViewById(R.id.List_Task);
            list_time = itemView.findViewById(R.id.List_Time);
            list_type = itemView.findViewById(R.id.List_Type);
        }
    }
}
