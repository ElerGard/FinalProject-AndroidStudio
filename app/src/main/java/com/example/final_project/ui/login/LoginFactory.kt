package com.example.final_project.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.data.Database

class LoginFactory(private val db: Database?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == LoginViewModel::class.java) {
            LoginViewModel(db) as T
        } else LoginViewModel(null) as T
    }
}