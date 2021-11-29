package com.example.games.ui.ListChosenGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.GamesItem
import com.example.games.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class ListChosenGamesViewModel(
    private val repository: MainRepository): ViewModel() {
    val fResponse: MutableLiveData<Response<MutableList<GamesItem>>> = MutableLiveData()
    val _gameItem = MutableLiveData<GamesItem>()
    val gameItem: LiveData<GamesItem> = _gameItem

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    fun getSelectedGame(platform : String){
        viewModelScope.launch {
            try {
                _progressBar.value = true
                val response = repository.getPlatform(platform)
                fResponse.postValue(response)
            }catch (ex: Exception){
                //tratamento do possivel erro
            }finally {
                _progressBar.value = false
            }

        }
    }
}