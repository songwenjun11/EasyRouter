package com.realize.easyrouter

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.realize.routeeasy.interfaces.Interceptor
import com.realize.routeeasy.utils.Router
import com.realize.router_note.EAction

@EAction(path = "action/activity/test")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_button).setOnClickListener {
            Router.getInstance()
                .startAction("login/activity", object : Interceptor {
                    override fun interceptor(action: String?, bundle: Bundle?): Boolean {
                        Log.e("test", bundle.toString())
                        return true
                    }
                })
        }
    }
}