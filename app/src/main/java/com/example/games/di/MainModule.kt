package com.example.games.di

import com.example.games.repository.MainRepository
import com.example.games.ui.ListChosenGames.ListChosenGamesViewModel
import com.example.games.ui.ListGames.ListGamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainRepository()
    }
    viewModel {
        ListGamesViewModel(
            repository = get()
        )
    }
    viewModel {
        ListChosenGamesViewModel(
            repository = get()
        )
    }
}