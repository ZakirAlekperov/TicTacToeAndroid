package com.zakiralekperov.android.tictactoe.ui.logic

import android.app.Activity.RESULT_OK
import android.view.View
import android.widget.SeekBar
import com.zakiralekperov.android.tictactoe.R
import com.zakiralekperov.android.tictactoe.databinding.ActivitySettingsBinding
import com.zakiralekperov.android.tictactoe.ui.SettingsActivity

class Listeners {

    private var settingsActivity = SettingsActivity()

    fun prevLevelOnClick(){
        settingsActivity.apply {
            settingsBinding.prevLevel.setOnClickListener {

                currentDifficult--

                if (currentDifficult == 0) {
                    settingsBinding.prevLevel.visibility = View.INVISIBLE
                } else if (currentDifficult == 1) {
                    settingsBinding.nextLevel.visibility = View.VISIBLE
                }

                updateLevel(settingsActivity.currentDifficult)
                settingsBinding.difficultLevel.text =
                    resources.getStringArray(R.array.game_level)[currentDifficult]
            }
        }

    }

    fun nextLevelOnClick(){
        settingsActivity.apply {
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

    }
    fun toBackOnClick(){
        settingsActivity.apply {
            setResult(RESULT_OK)
            onBackPressed()
        }

    }

    fun soundBarChange(){
        settingsActivity.apply {
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

    }
    fun checkBoxHorizontalChange(){
        settingsActivity.apply {
            settingsBinding.checkBoxHorizontal.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 1
                } else {
                    currentGameRules -= 1
                }

                updateRules(currentGameRules)
            }
        }

    }
    fun checkBoxVerticalChange(){
        settingsActivity.apply {
            settingsBinding.checkBoxVertical.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 2
                } else {
                    currentGameRules -= 2
                }

                updateRules(currentGameRules)
            }
        }
    }
    fun checkBoxDiagonalChange(){
        settingsActivity.apply {
            settingsBinding.checkBoxDiagonal.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    currentGameRules += 4
                } else {
                    currentGameRules -= 4
                }

                updateRules(currentGameRules)
            }
        }
    }
}