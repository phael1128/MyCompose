package com.example.component.presentation.ui.component3.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.component.data.model.ResRepo
import com.example.component.data.service.ComposeStudyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor(
    private val composeStudyService: ComposeStudyService
) : ViewModel() {
    val repos = mutableStateListOf<ResRepo>()

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = composeStudyService.listRepos("phael1128")
            Log.d("phael", "result : ${result.joinToString()}")
            repos.addAll(result)
        }
    }
}