package com.example.lifetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //CounterApp()
            AppNavigation()
        }
    }
}


@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { MainScreen(navController) }
        composable("mainapp") { CounterApp(navController) }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    var cStartingLife by remember { mutableStateOf("40") }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TextField(
            value = cStartingLife,
            onValueChange = { cStartingLife = it },
            label = { Text("Label") }
        )
        Button(onClick = { navController.navigate("mainapp") }) {
            Text("Start Lifetracker")
        }
    }
}
@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Settings", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}


@Composable
fun CounterApp( navController: NavHostController) {
    val players = remember { List(4) { Player(40) } }
    var visibleBox by remember { mutableStateOf<Int?>(null) }

    Box(Modifier.fillMaxSize()) {
        if (visibleBox == null) {
            val alignments = listOf(Alignment.TopStart, Alignment.TopEnd, Alignment.BottomStart, Alignment.BottomEnd)

            for (i in players.indices) {
                Box(Modifier.align(alignments[i])) {
                    PlayerBox(
                        player = players[i],
                        position = i,
                        onLongPress = {
                            visibleBox = if (visibleBox == i) null else i
                        }
                    )
                }
            }
        }

        visibleBox?.let {
            CommanderDamageBox(player = players[it], playerIndex = it) {
                visibleBox = null
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerBox(
    player: Player,
    position: Int,
    onLongPress: () -> Unit
) {
    val colors = listOf(Color(0xFF118ab2), Color(0xFFef476f), Color(0xFF06d6a0), Color(0xFFffd166))
    val rotation = listOf(90f, 270f, 90f, 270f)

    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f)
            .background(colors[position])
    ) {
        Text(
            "${player.currentLife}",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center).rotate(rotation[position]),
            fontSize = 60.sp
        )

        Box(
            Modifier
                .combinedClickable(onClick = { player.addLife() }, onLongClick = onLongPress)
                .align(if (position % 2 == 0) Alignment.TopEnd else Alignment.TopStart)
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
        ) {
            Text("+", Modifier.align(Alignment.Center), color = Color.White)
        }

        Button(
            onClick = { player.substractLife() },
            modifier = Modifier
                .align(if (position % 2 == 0) Alignment.TopStart else Alignment.TopEnd)
                .fillMaxWidth(0.5f)
                .fillMaxHeight(),
            shape = CutCornerShape(0),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0f))
        ) {
            Text("-", Modifier.rotate(90f))
        }
    }
}
