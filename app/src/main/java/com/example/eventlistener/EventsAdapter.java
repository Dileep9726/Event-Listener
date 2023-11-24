package com.example.eventlistener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsVH> {

    private final List<DataModel> model;
    private Context context;

    public EventsAdapter(Context context, List<DataModel> model) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.events_view, parent, false);
        return new EventsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsVH holder, int position) {

        DataModel dataModel = model.get(position);

        holder.tvEventName.setText(dataModel.getAction());
        holder.tvDesc.setText(dataModel.getDescription());
        holder.tvTime.setText(dataModel.getTime());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class EventsVH extends RecyclerView.ViewHolder {

        private final TextView tvEventName,tvDesc,tvTime;

        public EventsVH(@NonNull View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvDesc = itemView.findViewById(R.id.tvEventDesc);
            tvTime = itemView.findViewById(R.id.tvEventTime);
        }
    }
}
