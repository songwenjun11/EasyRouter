package com.realize.register_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realize.router_note.EAction

@EAction(path = "register/action")
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}