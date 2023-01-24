package com.zakiralekperov.android.tictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.zakiralekperov.android.tictactoe.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vsBotButton.setOnClickListener {
            gameVsBotStart()
        }

        binding.vsHumanButton.setOnClickListener {
            gameVsHumanStart()
        }

        binding.toBack.setOnClickListener {
            openMainActivity()
        }
    }

    private fun gameVsBotStart() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun gameVsHumanStart(){
        //TODO Реализовать игру 1 на 1
        Snackbar.make(binding.mainLayout, "Пока не реализованно", Snackbar.LENGTH_SHORT).show()
    }
    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}