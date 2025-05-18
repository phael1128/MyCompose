package com.example.component.presentation.ui.useful.component4.chap02

import android.content.Intent
import com.example.component.presentation.ui.useful.component4.chap02.activity.ContentActivity
import com.example.component.presentation.ui.useful.component4.chap02.activity.HomeActivity

class HomeState(
    val activity: HomeActivity
) {
    fun showContent(index: Int) {
        activity.startActivity(
            Intent(
                activity,
                ContentActivity::class.java
            ).apply {
            putExtra("id", index)
        }
        )
    }
}