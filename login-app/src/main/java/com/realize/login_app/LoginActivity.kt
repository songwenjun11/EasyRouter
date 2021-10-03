package com.realize.login_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.realize.routeeasy.utils.Router
import com.realize.router_note.EAction

@EAction(path = "login/activity")
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val tvBtnLogin = findViewById<TextView>(R.id.tv_btn_login);
        tvBtnLogin.setOnClickListener {
            Router.getInstance().startAction("main/activity")
        }
    }
}