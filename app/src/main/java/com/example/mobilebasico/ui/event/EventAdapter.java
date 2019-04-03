package com.example.mobilebasico.ui.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilebasico.R;
import com.example.mobilebasico.model.Events;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventsViewHolder> {

    List<Events> mEvents;
    Context mContext;

    public EventAdapter(List<Events> events, Context context){
        this.mEvents = events;
        this.mContext = context;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_events, parent,false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

        Events event = mEvents.get(position);

        holder.mDataEvento.setText(event.getDate_event().toString());
        holder.mDescricaoEvento.setText(event.getDescription());

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    static class EventsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textDataEvento)
        TextView mDataEvento;
        @BindView(R.id.textDescricaoEvento)
        TextView mDescricaoEvento;

        private EventsViewHolder(final View view){
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
