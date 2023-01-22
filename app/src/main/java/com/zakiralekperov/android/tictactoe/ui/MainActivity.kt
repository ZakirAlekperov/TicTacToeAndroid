package com.zakiralekperov.android.tictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zakiralekperov.android.tictactoe.constant.MainActivityConstant.EXTRA_TIME
import com.zakiralekperov.android.tictactoe.databinding.ActivityMainBinding
import com.zakiralekperov.android.tictactoe.constant.MainActivityConstant.EXTRA_GAME_FIELD
import com.zakiralekperov.android.tictactoe.controller.GameInfo
import android.view.View
import com.zakiralekperov.android.tictactoe.R


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getInfoAboutLastGame() : GameInfo {
        with(getSharedPreferences("game", MODE_PRIVATE)){
            val time = getLong("time", 0)
            val gameField = getString("gameField", "")

            return if (gameField != null) {
                GameInfo(time, gameField)
            } else {
                GameInfo(0,"")
            }
        }
    }

    fun toContinueGameOnClick(view: View){
        val gameInfo = getInfoAboutLastGame()
        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra(EXTRA_TIME, gameInfo.time)
            putExtra(EXTRA_GAME_FIELD, gameInfo.gameField)
        }
        startActivity(intent)
    }

    fun toSettingsOnClick(view: View){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun toNewGameOnClick(view: View){
        val intent = Intent(this, SelectActivity::class.java)
        startActivity(intent)
    }
}