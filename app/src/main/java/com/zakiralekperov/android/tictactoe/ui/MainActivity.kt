package com.zakiralekperov.android.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zakiralekperov.android.tictactoe.R
import com.zakiralekperov.android.tictactoe.controller.InfoGame
import com.zakiralekperov.android.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.toNewGame.setOnClickListener{
            toNewGameOnClick()
        }
        binding.toContinueGame.setOnClickListener{
            toContinueGameOnClick()
        }
        binding.toSettings.setOnClickListener{
            toSettingsOnClick()
        }
        setContentView(binding.root)
    }

    private fun getInfoAboutGame(): InfoGame {
        with(getSharedPreferences(
            getString(R.string.preference_file_key),
            MODE_PRIVATE
        )){
            val time = getLong(GameActivity.PREF_GAME_FIELD, 0L)
            val gameField = getString(GameActivity.PREF_TIME, "")

            if(gameField != null){
                return InfoGame(time, gameField)
            }
            return InfoGame(0L, "")
        }
    }

    private fun toSettingsOnClick(){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun toContinueGameOnClick(){
        val data = getInfoAboutGame()
        val intent = Intent(this,
            GameActivity::class.java)
            .apply {
                putExtra(EXTRA_TIME, data.time)
                putExtra(EXTRA_GAME_FIELD, data.gameField)
            }
        startActivity(intent)
    }

    private fun toNewGameOnClick(){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    companion object{
        const val EXTRA_TIME = "extra_time"
        const val EXTRA_GAME_FIELD = "extra_game_field"
    }
}