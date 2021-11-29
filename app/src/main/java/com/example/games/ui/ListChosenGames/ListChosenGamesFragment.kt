package com.example.games.ui.ListChosenGames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.R
import com.example.games.adapter.ListGames.ListChosenGamesAdapter
import kotlinx.android.synthetic.main.list_chosen_platform_games.*
import kotlinx.android.synthetic.main.list_games.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG_PC = "pc"
private const val TAG_BROWSER = "browser"
class ListChosenGamesFragment : Fragment() {
    private val listChosenGamesViewModel: ListChosenGamesViewModel by viewModel()
    private val arguments by navArgs<ListChosenGamesFragmentArgs>()
    private val adapter by lazy {
        context?.let {
            ListChosenGamesAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSelectedGames()
    }

    private fun getSelectedGames() {
        if (arguments.idButtonClick == 1) {
            listChosenGamesViewModel.getSelectedGame(TAG_PC)
            listChosenGamesViewModel.fResponse.observe(this, { response ->
                if (response.isSuccessful) {
                    response.body()?.let { gamesSelected ->
                        adapter?.add(gamesSelected)
                    }
                } else {
                    Log.i("Response", response.errorBody().toString())
                    Toast.makeText(context, "Erro encontrado!", Toast.LENGTH_LONG).show()
                }

            })
        } else if (arguments.idButtonClick == 2) {
            listChosenGamesViewModel.getSelectedGame(TAG_BROWSER)
            listChosenGamesViewModel.fResponse.observe(this, { response ->
                if(response.isSuccessful){
                    response.body()?.let { gamesSelected ->
                        adapter?.add(gamesSelected)
                    }
                }else {
                    Log.i("Response", response.errorBody().toString())
                    Toast.makeText(context, "Erro encontrado!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_chosen_platform_games,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRV()
        configList()

        listChosenGamesViewModel.progressBar.observe(viewLifecycleOwner){showProgressBar ->
            if(showProgressBar){
                progressBar_list_chosen_platform_games.visibility = View.VISIBLE
            }else{
                progressBar_list_chosen_platform_games.visibility = View.GONE
            }
        }
    }

    private fun configList() {
        rv_list_chosen_games.adapter = adapter
        rv_list_chosen_games.layoutManager = LinearLayoutManager(context)
    }

    private fun configRV() {
        rv_list_chosen_games.adapter = adapter
    }
}