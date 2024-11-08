package com.example.mod4rolldice.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mod4rolldice.DiceState
import com.example.mod4rolldice.DiceViewModel
import com.example.mod4rolldice.R
import com.example.mod4rolldice.ui.theme.TPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RollDicePage( Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RollDicePage(modifier: Modifier = Modifier,
                 vm: DiceViewModel = viewModel()) {
    val diceState = vm.diceState.collectAsState().value
    Column(modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        if(diceState !is DiceState.DiceInitial)
            Image(
                when(diceState.resultDice){
              1->painterResource(R.drawable.d1)
              2->painterResource(R.drawable.d2)
              3->painterResource(R.drawable.d3)
              4->painterResource(R.drawable.d4)
              5->painterResource(R.drawable.d5)
              6->painterResource(R.drawable.d6)
              else ->painterResource(R.drawable.d1)
          },contentDescription = "d1")
        Text("Total lancer : ${diceState.totalRolls}")
        Row{
            Spacer(Modifier.weight(1f))
            Text("Total = ${diceState.scoreL}/${diceState.numberRollsL}lancers")
            Spacer(Modifier.weight(1f))
           Text("Total = ${diceState.scoreR}/${diceState.numberRollsR}lancers")
            Spacer(Modifier.weight(1f))

        }
        Row{
            Button({ vm.rollLeft() },
                enabled = diceState !is DiceState.ResultRollL,
                modifier = Modifier.weight(0.5f)) {
                Text("Gauche")
            }
            Button({ vm.rollRight() } ,
                enabled = diceState  !is DiceState.ResultRollR,
                modifier = Modifier.weight(0.5f)) {
                Text("Droite")
            }
       }
        Button({vm.reset()}) {Text("Recommencer") }

    }
}