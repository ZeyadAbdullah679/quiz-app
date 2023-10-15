package com.example.quizapp.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsUtil(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    fun saveUserScore(score: Int) {
        sharedPreferences.edit().putInt("score", score).apply()
    }

    fun getUserScore(): Int {
        return sharedPreferences.getInt("score", 0)
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString("name", name).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("name", null)
    }
}
