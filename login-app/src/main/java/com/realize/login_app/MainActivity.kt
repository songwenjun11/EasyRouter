package com.realize.login_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realize.router_note.EAction

@EAction(path = "login/main")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}