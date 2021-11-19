package com.example.games.ui.ListGames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.games.R
import com.example.games.adapter.ListGames.ListGamesAdapter
import com.example.games.model.GamesItem
import kotlinx.android.synthetic.main.item_list_games.*
import kotlinx.android.synthetic.main.list_games.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListGamesFragment() : Fragment() {
    private val listGamesViewModel: ListGamesViewModel by viewModel()
    private val adapter by lazy {
        context?.let {
            ListGamesAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getGames()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_games,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRV()
        configList()
    }

    private fun configList() {
        rv_list_games.adapter = adapter
        rv_list_games.layoutManager = LinearLayoutManager(context)
    }

    private fun configRV() {
        rv_list_games.adapter = adapter
    }

    private fun getGames() {
        listGamesViewModel.getGames()
        listGamesViewModel.fResponse.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let { games ->
                    adapter?.add(games)
                }
            } else {
                Log.i("Response", response.errorBody().toString())
                Toast.makeText(context, "Erro encontrado!", Toast.LENGTH_LONG).show()
            }
        })
    }
}