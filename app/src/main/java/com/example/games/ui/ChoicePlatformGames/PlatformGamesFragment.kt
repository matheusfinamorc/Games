package com.example.games.ui.ChoicePlatformGames

import android.os.Bundle
import androidx.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.games.R
import kotlinx.android.synthetic.main.choice_platform_games.*

class PlatformGamesFragment : Fragment() {

    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.choice_platform_games,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configCLick()
    }

    private fun configCLick() {
        button_platform_pc.setOnClickListener {
            val direction = PlatformGamesFragmentDirections
                .actionChoicePlatformGamesToListChosenGamesFragment(1)
            controller.navigate(direction)

        }
        button_platform_browser.setOnClickListener {
            val direction = PlatformGamesFragmentDirections
                .actionChoicePlatformGamesToListChosenGamesFragment(2)
            controller.navigate(direction)

        }
    }
}
