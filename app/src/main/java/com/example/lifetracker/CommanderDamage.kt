package com.example.lifetracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CommanderDamage(startingCommanderDamage: Int) {
    var currentCommanderLife by mutableStateOf(startingCommanderDamage)
        private set

    fun addLife() {
        currentCommanderLife++
    }
    fun substractLife() {
        if (currentCommanderLife != 0) {
            currentCommanderLife--
        }
    }
}