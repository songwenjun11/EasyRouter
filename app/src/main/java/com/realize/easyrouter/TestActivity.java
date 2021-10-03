package com.realize.easyrouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.realize.router_note.EAction;

@EAction(path = "action/activity/test")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String abc = getIntent().getExtras().getString("abc");
        TextView textView = findViewById(R.id.tv_button);
        textView.setText(abc);
    }
}