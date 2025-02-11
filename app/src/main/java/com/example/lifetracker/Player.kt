package com.example.lifetracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class Player(startingLife: Int) {
    var currentLife by mutableStateOf(startingLife)
        private set

     companion object{
         const val DEFAULT_LIFE: Int = 40
     }

    val dmgFrom1 = CommanderDamage(0)
    val dmgFrom2 = CommanderDamage(0)
    val dmgFrom3 = CommanderDamage(0)
    val dmgFrom4 = CommanderDamage(0)

    fun addLife() {

        currentLife++
    }
    fun substractLife() {
        currentLife--
    }
}