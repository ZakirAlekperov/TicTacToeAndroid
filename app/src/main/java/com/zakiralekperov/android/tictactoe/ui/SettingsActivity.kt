package com.zakiralekperov.android.tictactoe.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


class SettingsActivity : AppCompatActivity() {

    lateinit var settingsBinding: ActivitySettingsBinding

    val TAG = "Main"
    var currentDifficult = 0
    var currentVolume = 0
    var currentGameRules = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)
        Log.d(TAG, "лейаут настроек задан")

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
        if (currentDifficult == EASY_DIFFICULT) {
            settingsBinding.prevLevel.visibility = View.INVISIBLE
            settingsBinding.nextLevel.visibility = View.VISIBLE
        }
    }
    private fun setVisibleNextLevelButton(){
        if (currentDifficult == HARD_DIFFICULT) {
            settingsBinding.prevLevel.visibility = View.VISIBLE
            settingsBinding.nextLevel.visibility = View.INVISIBLE
        }
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
        prevLevelOnClick()
        nextLevelOnClick()
        soundBarChange()
        checkBoxHorizontalChange()
        checkBoxVerticalChange()
        checkBoxDiagonalChange()
        toBackOnClick()
    }

    fun prevLevelOnClick(){
            settingsBinding.prevLevel.setOnClickListener {

                currentDifficult--

                if (currentDifficult == 0) {
                    settingsBinding.prevLevel.visibility = View.INVISIBLE
                } else if (currentDifficult == 1) {
                    settingsBinding.nextLevel.visibility = View.VISIBLE
                }

                updateLevel(currentDifficult)
                settingsBinding.difficultLevel.text =
                    resources.getStringArray(R.array.game_level)[currentDifficult]
            }


    }

    fun nextLevelOnClick(){
            settingsBinding.nextLevel.setOnClickListener {
                currentDifficult++

                if (currentDifficult == 2) {
                    settingsBinding.nextLevel.visibility = View.INVISIBLE
                } else if (currentDifficult == 1) {
                    settingsBinding.prevLevel.visibility = View.VISIBLE
                }

                updateLevel(currentDifficult)
                settingsBinding.difficultLevel.text =
                    resources.getStringArray(R.array.game_level)[currentDifficult]

        }

    }
    fun toBackOnClick(){
        settingsBinding.toBack.setOnClickListener {
            setResult(Activity.RESULT_OK)
            onBackPressed()
        }
    }

    fun soundBarChange(){
            settingsBinding.soundBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    currentVolume = progress
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    updateVolumeSound(currentVolume)
                }
            })
    }
    fun checkBoxHorizontalChange(){
            settingsBinding.checkBoxHorizontal.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 1
                } else {
                    currentGameRules -= 1
                }

                updateRules(currentGameRules)
            }
    }
    fun checkBoxVerticalChange(){
            settingsBinding.checkBoxVertical.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 2
                } else {
                    currentGameRules -= 2
                }

                updateRules(currentGameRules)
            }
    }
    fun checkBoxDiagonalChange(){
            settingsBinding.checkBoxDiagonal.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 4
                } else {
                    currentGameRules -= 4
                }

                updateRules(currentGameRules)
            }
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