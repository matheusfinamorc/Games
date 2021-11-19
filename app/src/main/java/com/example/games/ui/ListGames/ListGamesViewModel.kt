package com.example.games.ui.ListGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.GamesItem
import com.example.games.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListGamesViewModel(
    private val repository: MainRepository
): ViewModel() {
    val fResponse: MutableLiveData<Response<MutableList<GamesItem>>> = MutableLiveData()
    val _gameItem = MutableLiveData<GamesItem>()
    val gameItem: LiveData<GamesItem> = _gameItem

    fun getGames(){
        viewModelScope.launch {
            val response = repository.getGames()
            fResponse.postValue(response)
        }
    }
}