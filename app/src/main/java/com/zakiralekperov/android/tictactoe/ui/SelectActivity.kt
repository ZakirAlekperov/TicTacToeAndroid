package com.zakiralekperov.android.tictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zakiralekperov.android.tictactoe.R

class SelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
    }

    fun vsCompButtonOnClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
    fun vsHumanButtonOnClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}