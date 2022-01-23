package com.example.final_project.ui.battleship.view

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BattleshipViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Please enter your nickname and press button"
    }
    val text: LiveData<String> = _text

    private val _but = MutableLiveData<Int>().apply {
        value = View.GONE
    }
    val but: LiveData<Int> = _but
}