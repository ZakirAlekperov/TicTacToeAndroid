package com.zakiralekperov.android.tictactoe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.zakiralekperov.android.tictactoe.R
import com.zakiralekperov.android.tictactoe.constant.SettingsActivityConstant.EASY_DIFFICULT
import com.zakiralekperov.android.tictactoe.constant.SettingsActivityConstant.HARD_DIFFICULT
import com.zakiralekperov.android.tictactoe.constant.SettingsActivityConstant.PREF_LEVEL
import com.zakiralekperov.android.tictactoe.constant.SettingsActivityConstant.PREF_RULES
import com.zakiralekperov.android.tictactoe.constant.SettingsActivityConstant.PREF_SOUND
import com.zakiralekperov.android.tictactoe.databinding.ActivitySettingsBinding
import com.zakiralekperov.android.tictactoe.model.SettingsInfo
import com.zakiralekperov.android.tictactoe.ui.logic.Listeners


class SettingsActivity : AppCompatActivity() {

    lateinit var settingsBinding: ActivitySettingsBinding
    private lateinit var listeners: Listeners

    var currentDifficult = 0
    var currentVolume = 0
    var currentGameRules = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)

        setCurrentSettings()

        setVisibleDifficultSwitch()

        setRulesCheckBox()

        setOnClickListeners()





        settingsBinding.difficultLevel.text = resources.getStringArray(R.array.game_level)[currentDifficult]
        settingsBinding.soundBar.progress = currentVolume
    }

    private fun getCurrentSettings(): SettingsInfo {
        this.getSharedPreferences("game", MODE_PRIVATE).apply {

            val sound = getInt(PREF_SOUND, 100)
            val level = getInt(PREF_LEVEL, 1)
            val rules = getInt(PREF_RULES, 7)

            return SettingsInfo(sound, level, rules)
        }
    }

    private fun setCurrentSettings(){
        val currentSettings = getCurrentSettings()

        currentDifficult = currentSettings.level
        currentVolume = currentSettings.sound
        currentGameRules = currentSettings.rules
    }

    private fun setVisibleDifficultSwitch(){
        setVisiblePrevLevelButton()
        setVisibleNextLevelButton()
    }

    private fun setVisiblePrevLevelButton() {
        if (currentDifficult == EASY_DIFFICULT)
            settingsBinding.prevLevel.visibility = View.INVISIBLE
    }
    private fun setVisibleNextLevelButton(){
        if (currentDifficult == HARD_DIFFICULT)
            settingsBinding.nextLevel.visibility = View.INVISIBLE
    }

    private fun setRulesCheckBox(){
        when(currentGameRules){
            1 -> settingsBinding.checkBoxHorizontal.isChecked = true
            2 -> settingsBinding.checkBoxVertical.isChecked = true
            3 -> {
                settingsBinding.checkBoxHorizontal.isChecked = true
                settingsBinding.checkBoxVertical.isChecked = true
            }
            4 -> settingsBinding.checkBoxDiagonal.isChecked = true
            5 -> {
                settingsBinding.checkBoxDiagonal.isChecked = true
                settingsBinding.checkBoxHorizontal.isChecked = true
            }
            6 -> {
                settingsBinding.checkBoxDiagonal.isChecked = true
                settingsBinding.checkBoxVertical.isChecked = true
            }
            7 -> {
                settingsBinding.checkBoxHorizontal.isChecked = true
                settingsBinding.checkBoxVertical.isChecked = true
                settingsBinding.checkBoxDiagonal.isChecked = true
            }
        }
    }

    private fun setOnClickListeners(){
        listeners.prevLevelOnClick()
        listeners.nextLevelOnClick()
        listeners.soundBarChange()
        listeners.checkBoxHorizontalChange()
        listeners.checkBoxVerticalChange()
        listeners.checkBoxDiagonalChange()
        listeners.toBackOnClick()
    }


    fun updateVolumeSound(volume: Int){
        getSharedPreferences("game", MODE_PRIVATE).edit().apply{
            putInt(PREF_SOUND, volume)
            apply()
        }
        setResult(RESULT_OK)
    }

    fun updateLevel(level: Int){
        getSharedPreferences("game", MODE_PRIVATE).edit().apply {
            putInt(PREF_LEVEL, level)
            apply()
        }
        setResult(RESULT_OK)
    }

    fun updateRules(rules: Int){
        getSharedPreferences("game", MODE_PRIVATE).edit().apply {
            putInt(PREF_RULES, rules)
            apply()
        }
        setResult(RESULT_OK)
    }

}