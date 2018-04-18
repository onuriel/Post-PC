package com.example.orenn.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messages;

    public MessageAdapter(List<Message> input){
        this.messages = input;
    }

    public void addMessage(Message msg) {
        messages.add(msg);
        notifyItemInserted(messages.size() -1 );
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message msg = messages.get(position);
        holder.name.setText(msg.getmName());

        holder.message.setText(msg.getmMessage());
        holder.time.setText(String.format( "%tF %<tT", msg.getTime()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }



    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView name, message, time;

        public MessageViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.timestamp);
        }
    }
}
