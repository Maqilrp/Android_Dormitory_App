package com.festra.dormitory.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _userUid = MutableStateFlow<String?>(null)
    val userUid: StateFlow<String?> = _userUid

    init {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser
            _userUid.emit(currentUser?.uid) // Emit UID if user is signed in
        }
    }
    fun setUserUid(uid: String) {
        require(uid.isNotBlank()) { "UID cannot be empty or whitespace." }
        viewModelScope.launch {
            _userUid.emit(uid)
        }
    }
    // ... other functions in your UserViewModel
}