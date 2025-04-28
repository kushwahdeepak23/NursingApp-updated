package com.example.nursingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<String?>(null)
    val authState: StateFlow<String?> = _authState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    _authState.value = if (it.isSuccessful) "Registered" else it.exception?.message
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    _authState.value = if (it.isSuccessful) "LoggedIn" else it.exception?.message
                }
        }
    }
}
