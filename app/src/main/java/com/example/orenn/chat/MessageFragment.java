package com.example.orenn.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MessageFragment extends DialogFragment {
    private static final String MESSAGE_ARG = "message_arg";
    private static final String NAME_ARG = "name_arg";
    private static final String TIME_ARG = "time_arg";
    private static final String POS_ARG = "position";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment,container, false);
        View layout = view.findViewById(R.id.message_fragment_layout);
        final TextView message = view.findViewById(R.id.fragment_message);
        message.setText(getArguments().getString(MESSAGE_ARG));
        TextView name = view.findViewById(R.id.fragment_name);
        name.setText(getArguments().getString(NAME_ARG));
        TextView time = view.findViewById(R.id.fragment_timestamp);
        time.setText(getArguments().getString(TIME_ARG));
        Button deleteButton = view.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = getActivity().findViewById(R.id.list);
                int position = getArguments().getInt(POS_ARG);
                MessageAdapter messageAdapter = (MessageAdapter) recyclerView.getAdapter();
                messageAdapter.removeMessage(position);
                dismiss();
            }
        });
        return view;
    }

    public static MessageFragment newInstance(String name, String message, String time, int position){
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE_ARG, message);
        bundle.putString(NAME_ARG, name);
        bundle.putString(TIME_ARG, time);
        bundle.putInt(POS_ARG, position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
