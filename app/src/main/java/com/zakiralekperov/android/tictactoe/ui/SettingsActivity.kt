package com.zakiralekperov.android.tictactoe.ui

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.zakiralekperov.android.tictactoe.R
import com.zakiralekperov.android.tictactoe.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private var currentSoundValue = 0
    private var currentLevel = 0
    private var currentRules = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = getSettingsInfo()

        currentSoundValue = data.soundValue
        currentLevel =data.level
        currentRules = data.rules

        when(currentRules){
            1 -> binding.checkBoxVertical.isChecked = true
            2 -> binding.checkBoxHorizontal.isChecked = true
            3 -> {
                binding.checkBoxVertical.isChecked = true
                binding.checkBoxHorizontal.isChecked = true
            }
            4 -> binding.checkBoxDiagonal.isChecked = true
            5 -> {
                binding.checkBoxVertical.isChecked = true
                binding.checkBoxDiagonal.isChecked = true
            }
            6 -> {
                binding.checkBoxHorizontal.isChecked = true
                binding.checkBoxDiagonal.isChecked = true
            }
            7 ->{
                binding.checkBoxVertical.isChecked = true
                binding.checkBoxHorizontal.isChecked = true
                binding.checkBoxDiagonal.isChecked = true
            }
        }

        if(currentLevel ==0){
            binding.prevLevel.visibility = View.INVISIBLE
        }else if(currentLevel ==2){
            binding.nextLevel.visibility = View.INVISIBLE
        }

        binding.infoLevel.text = resources.getStringArray(R.array.game_level)[currentLevel]
        binding.soundBar.progress  = currentSoundValue
        binding.toBack.setOnClickListener{
            onBackPressed()
        }

        binding.prevLevel.setOnClickListener{
            currentLevel--

            if(currentLevel ==0){
                binding.prevLevel.visibility = View.INVISIBLE
            }else if(currentLevel ==1){
                binding.nextLevel.visibility = View.VISIBLE
            }

            binding.infoLevel.text = resources.getStringArray(R.array.game_level)[currentLevel]

            updateLevel(currentLevel)
        }


        binding.nextLevel.setOnClickListener{
            currentLevel++

            if(currentLevel ==1){
                binding.prevLevel.visibility = View.VISIBLE
            }else if(currentLevel ==2){
                binding.nextLevel.visibility = View.INVISIBLE
            }

            binding.infoLevel.text = resources.getStringArray(R.array.game_level)[currentLevel]

            updateLevel(currentLevel)
        }


        binding.soundBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentSoundValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               // updateSoundLevel(currentSoundValue)
            }
        })
/*
        binding.checkBoxVertical.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                currentRules++
            }else{
                currentLevel--
            }
            updateRules(currentRules)
        }
        binding.checkBoxHorizontal.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                currentRules+=2
            }else{
                currentLevel-=2
            }
            updateRules(currentRules)
        }
        binding.checkBoxDiagonal.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                currentRules+=4
            }else{
                currentLevel-=4
            }
            updateRules(currentRules)
        }


 */
    }
    private fun updateSoundLevel(value:Int){
       with(getSharedPreferences(
           getString(R.string.preference_file_key),MODE_PRIVATE).edit()){
           putInt(PREF_SOUND_VALUE, value)
           apply()
       }

        setResult(RESULT_OK)
    }

    private fun updateLevel(level: Int){
        with(getSharedPreferences(
            getString(R.string.preference_file_key),MODE_PRIVATE).edit()){
            putInt(PREF_LEVEL, level)
            apply()
        }
        setResult(RESULT_OK)
    }
    private fun updateRules(rules:Int){
        with(getSharedPreferences(
            getString(R.string.preference_file_key),MODE_PRIVATE).edit()){
            putInt(PREF_RULES, rules)
            apply()
        }

        setResult(RESULT_OK)
    }

    private fun getSettingsInfo(): SettingsInfo {
        with(getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)){
            val soundValue = getInt(PREF_SOUND_VALUE, 50)
            val level = getInt(PREF_LEVEL, 0)
            val rules = getInt(PREF_RULES, 7)

            return SettingsInfo(soundValue, level, rules)
        }
    }

    data class SettingsInfo(val soundValue: Int, val level: Int, val rules: Int)

    companion object{
        const val PREF_SOUND_VALUE = "pref_sound_value"
        const val PREF_LEVEL = "pref_level"
        const val PREF_RULES = "pref_rules"
    }
}