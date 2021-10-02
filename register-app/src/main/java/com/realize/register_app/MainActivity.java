package com.realize.register_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.realize.routeeasy.utils.Router;
import com.realize.router_note.EAction;

@EAction(path = "register/action")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvButton = findViewById(R.id.tv_button);
        tvButton.setOnClickListener(v ->
                Router.getInstance().startAction("fragment/test?asb=iushdksd"));
    }
}