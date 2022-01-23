package com.example.final_project.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final_project.data.Database
import com.example.final_project.data.User

class LoginViewModel(db: Database?) : ViewModel() {

    private val db: Database? = null

    private val _dataValid = MutableLiveData<Boolean>()
    val dataValid: LiveData<Boolean> = _dataValid

    var error: MutableLiveData<String>? = null

    private var _usernameToLogin = MutableLiveData<String?>()
    val usernameToLogin: LiveData<String?> = _usernameToLogin

    private val _users = MutableLiveData<MutableList<User>>().apply {
        if (db != null) {
            value = db.getUsers()
        }
    }
    val users: LiveData<MutableList<User>> = _users

    fun isDataValid(username: String, password: String)
    {
        if (error == null)
        {
            error = MutableLiveData<String>()
        }
        if (username.isBlank() || password.isBlank())
        {
            error!!.value = "Login or Password cannot be blank"
            _dataValid.value = false
        }
        else if (username.length < 3)
        {
            error!!.value = "Login must be more than 2 characters"
            _dataValid.value = false
        }
        else if (password.length < 6)
        {
            error!!.value = "Password must be more than 6 characters"
            _dataValid.value = false
        }
        else
        {
            error!!.value = ""
            _dataValid.value = true
        }
    }

    fun login(username: String, password: String)
    {
        if (db != null) {
            if (db.getUser(username, password) == null)
                _usernameToLogin.value = null
            else
            {
                _usernameToLogin.value = String()
                _usernameToLogin.value = username

            }
        }
        else
            _usernameToLogin.value = null
    }
}