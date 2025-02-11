package com.example.lifetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CommanderDamageBox(player: Player, playerIndex: Int, onClose: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        val colors = listOf(Color(0xFF007197), Color(0xFFD02457), Color(0xFF00BA86), Color(0xFFE1B64C))
        val damages = listOf(
            player.dmgFrom1.currentCommanderLife,
            player.dmgFrom2.currentCommanderLife,
            player.dmgFrom3.currentCommanderLife,
            player.dmgFrom4.currentCommanderLife
        )
        val addDamages = listOf(
            { player.dmgFrom1.addLife() },
            { player.dmgFrom2.addLife() },
            { player.dmgFrom3.addLife() },
            { player.dmgFrom4.addLife() }
        )
        val substractDamages = listOf(
            { player.dmgFrom1.substractLife() },
            { player.dmgFrom2.substractLife() },
            { player.dmgFrom3.substractLife() },
            { player.dmgFrom4.substractLife() }
        )

        val alignments = listOf(Alignment.TopStart, Alignment.TopEnd, Alignment.BottomStart, Alignment.BottomEnd)
        val rotation = listOf(90f, 270f, 90f, 270f)

        for (i in damages.indices) {
            if (i == playerIndex) {
                Box(
                    modifier = Modifier
                        .align(alignments[i])
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .background(colors[i])
                        .clickable { onClose() }
                ) {
                    Text(
                        "Click to close commander damage",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .rotate(rotation[i]),
                        fontSize = 20.sp
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .align(alignments[i])
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .background(colors[i])
                ) {
                    Text(
                        "Commander dmg: ${damages[i]}",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .rotate(rotation[i]),
                        fontSize = 20.sp
                    )
                    Box(
                        Modifier
                            .clickable(onClick = { addDamages[i]() })
                            .align(if (i % 2 == 0) Alignment.TopEnd else Alignment.TopStart)
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                    ) {
                        Text("+", Modifier.align(Alignment.Center), color = Color.White)
                    }

                    Button(
                        onClick = { substractDamages[i]() },
                        modifier = Modifier
                            .align(if (i % 2 == 0) Alignment.TopStart else Alignment.TopEnd)
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(),
                        shape = CutCornerShape(0),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0f))
                    ) {
                        Text("-", Modifier.rotate(90f))
                    }
                }
            }
        }
    }
}
