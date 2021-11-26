package com.example.games.adapter.ListGames

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.games.databinding.ItemListGamesBinding
import com.example.games.model.GamesItem
import kotlinx.android.synthetic.main.item_list_games.view.*

class ListChosenGamesAdapter(
    private val context: Context,
    private val games: MutableList<GamesItem> = mutableListOf()
) : RecyclerView.Adapter<ListChosenGamesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListChosenGamesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemListGamesBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListChosenGamesAdapter.ViewHolder, position: Int) {
        holder.bindingDataListChosenGames(games[position])
    }

    override fun getItemCount() = games.size

    fun add(games: List<GamesItem>) {
        this.games.clear()
        this.games.addAll(games)
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItemListGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image by lazy { itemView.img_list }
        fun bindingDataListChosenGames(gameItem: GamesItem) {
            binding.games = gameItem
            binding.executePendingBindings()
            Glide.with(itemView)
                .load(gameItem.thumbnail)
                .override(600,200)
                .into(image)
        }
    }
}
