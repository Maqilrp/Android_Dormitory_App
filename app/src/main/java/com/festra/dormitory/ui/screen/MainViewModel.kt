package com.festra.dormitory.ui.screen

import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    fun onButtonClick() {
        Firebase.analytics.logEvent("clicked", null)
    }
}