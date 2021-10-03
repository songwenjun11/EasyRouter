package com.realize.easyrouter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.realize.routeeasy.utils.Router
import com.realize.router_note.EAction

@EAction(path = "main/activity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_button).setOnClickListener {
            Router.getInstance().startAction("login/activity")
        }
    }
}