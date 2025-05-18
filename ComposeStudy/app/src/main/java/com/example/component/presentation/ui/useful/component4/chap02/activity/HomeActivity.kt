package com.example.component.presentation.ui.useful.component4.chap02.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.component.presentation.ui.useful.component4.chap02.HomeState
import com.example.component.presentation.ui.useful.component4.chap02.screen.HomeScreen

class HomeActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                HomeState(this)
            )
        }
    }
}