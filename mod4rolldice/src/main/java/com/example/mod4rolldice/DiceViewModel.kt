package com.example.mod4rolldice

import androidx.lifecycle.ViewModel
import com.example.mod4rolldice.DiceState.DiceInitial.numberRollsL
import com.example.mod4rolldice.DiceState.DiceInitial.numberRollsR
import com.example.mod4rolldice.DiceState.DiceInitial.scoreL
import com.example.mod4rolldice.DiceState.DiceInitial.scoreR
import com.example.mod4rolldice.DiceState.DiceInitial.totalRolls
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
enum class Player{LEFT,RIGHT}
class DiceViewModel : ViewModel() {
    private val _diceState  = MutableStateFlow<DiceState>(DiceState.DiceInitial)
    val diceState = _diceState.asStateFlow()

    private fun _roll(player :Player){
        if((player == Player.LEFT &&
            _diceState.value is DiceState.ResultRollL) ||
            (player == Player.RIGHT &&
            _diceState.value is DiceState.ResultRollR)
        ) return;

        val resultDice = (0..6).random()
        _diceState.value = (if(player ==Player.LEFT)
            DiceState.ResultRollL()
        else
            DiceState.ResultRollR())
        .apply {
            totalRolls = _diceState.value.totalRolls + 1
            numberRollsL = _diceState.value.numberRollsL +
                    if(player == Player.LEFT) 1 else 0
            numberRollsR = _diceState.value.numberRollsR+
                    if(player == Player.RIGHT) 1 else 0

            scoreL = _diceState.value.scoreL+
                    if(player == Player.LEFT) resultDice else 0
            scoreR = _diceState.value.scoreR+
                if(player == Player.RIGHT) resultDice else 0
            this.resultDice = resultDice
        }
    }
    fun rollLeft(){_roll(Player.LEFT) }
    fun rollRight(){_roll(Player.RIGHT) }
    fun reset() {
        _diceState.value = DiceState.DiceInitial
    }

}

sealed class DiceState(var totalRolls:Int = 0,
                       var numberRollsL:Int=0, var numberRollsR:Int=0,
                       var scoreL:Int=0, var scoreR:Int=0,
                       var resultDice:Int=0
    ) {
    object DiceInitial : DiceState()
     class ResultRollL() : DiceState()
     class ResultRollR() : DiceState()
}