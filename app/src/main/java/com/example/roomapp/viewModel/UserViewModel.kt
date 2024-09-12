package com.example.roomapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
 import androidx.lifecycle.viewModelScope
import com.example.roomapp.ModelClass.User
import com.example.roomapp.UserDatabase
import com.example.roomapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }

    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteAllUser()
        }

    }
}
