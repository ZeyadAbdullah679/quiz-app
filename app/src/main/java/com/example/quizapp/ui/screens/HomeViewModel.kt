package com.example.quizapp.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _name = mutableStateOf("")
    val name: MutableState<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}