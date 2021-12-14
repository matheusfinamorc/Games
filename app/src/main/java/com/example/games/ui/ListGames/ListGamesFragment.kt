package com.example.games.ui.ListGames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.example.games.R
import com.example.games.adapter.ListGames.ListGamesAdapter
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
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
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

        listGamesViewModel.progressBar.observe(viewLifecycleOwner) { showProgressBar ->
            if (showProgressBar) {
                progressBar_list_games.visibility = View.VISIBLE
            } else {
                progressBar_list_games.visibility = View.GONE
            }

        }
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
                    // hideProgressBar()
                }
            } else {
                Log.i("Response", response.errorBody().toString())
                Toast.makeText(context, "Erro encontrado!", Toast.LENGTH_LONG).show()
            }
        })
    }
//    private fun hideProgressBar(){
//        progressBar_list_games.visibility = View.GONE
//    }
}