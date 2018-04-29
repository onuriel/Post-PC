package com.example.orenn.chat;

import android.arch.core.util.Function;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button mSend;
    RecyclerView mList;
    EditText mInput, mName;
    MessageFragment messageFragment;

    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.list);
        messageAdapter = new MessageAdapter(new ArrayList<Message>(), new RecyclerView.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = v.findViewById(R.id.name);
                TextView message = v.findViewById(R.id.message);
                TextView time = v.findViewById(R.id.timestamp);
                int position = mList.getChildAdapterPosition(v);
                create_fragment(name.getText().toString(), message.getText().toString(), time.getText().toString(), position);
            }
        });
        mList.setAdapter(messageAdapter);
        mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSend = findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(mInput.getText()) || TextUtils.isEmpty(mName.getText()))
                {
                    return;
                }
                Message msg = new Message(mName.getText().toString().concat(": "), mInput.getText().toString());
                messageAdapter.addMessage(msg);
                mInput.setText("");
                mList.smoothScrollToPosition(mList.getAdapter().getItemCount()-1);
            }
        });

        mInput = findViewById(R.id.input_text);
        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mName.getText())){
                    mSend.setEnabled(!TextUtils.isEmpty(s));
                }
            }
        });

        mName = findViewById(R.id.name_input);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mInput.getText())){
                    mSend.setEnabled(!TextUtils.isEmpty(s));
                }
            }
        });

    }

    public void create_fragment(String name, String message, String date, int position ){
        messageFragment = MessageFragment.newInstance(name, message, date, position);
        FragmentManager fm = getSupportFragmentManager();
        messageFragment.show(fm, "");

    }


}
