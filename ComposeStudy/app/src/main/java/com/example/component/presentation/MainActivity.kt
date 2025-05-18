package com.example.component.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.component.presentation.ui.basic.component3.chap08.PokemonActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Button(
                    onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                PokemonActivity::class.java
                            )
                        )
                    }
                ) {
                    Text(text = "포켓몬 화면으로 가기")
                }
            }
        }
    }
}

//fun Activity.unEnabledEdgeToEdge() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        window.setDecorFitsSystemWindows(false)
//        val controller = window.insetsController
//        if (controller != null) {
//            controller.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//            // 상태바, 내비게이션 바 색상 지정 가능
//            window.statusBarColor = Color.TRANSPARENT
//            window.navigationBarColor = Color.TRANSPARENT
//        }
//    } else {
//        // Android 10 이하 호환 코드
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        window.statusBarColor = Color.TRANSPARENT
//        window.navigationBarColor = Color.TRANSPARENT
//    }
//}