package com.example.mod4rolldice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiceViewModel : ViewModel() {
    private val _dice = MutableStateFlow(0)
    val dice = _dice.asStateFlow()

    fun roll(){
        _dice.value=(1..6).random()
    }
}