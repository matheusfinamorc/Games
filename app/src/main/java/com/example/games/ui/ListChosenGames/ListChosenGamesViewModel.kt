package com.example.games.ui.ListChosenGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.GamesItem
import com.example.games.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListChosenGamesViewModel(
    private val repository: MainRepository): ViewModel() {
    val fResponse: MutableLiveData<Response<MutableList<GamesItem>>> = MutableLiveData()
    val _gameItem = MutableLiveData<GamesItem>()
    val gameItem: LiveData<GamesItem> = _gameItem

    fun getSelectedGame(platform : String){
        viewModelScope.launch {
            val response = repository.getPlatform(platform)
                fResponse.postValue(response)
        }
    }
}