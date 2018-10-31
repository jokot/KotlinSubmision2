package com.example.jokot.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.database.Favorite
import org.jetbrains.anko.find

class FavoriteTeamsAdapter(private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}


class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //    private val teamBadge: ImageView = view.find(team_badge)
    private val homeTeam: TextView = view.find(R.id.homename)
    private val awayTeam: TextView = view.find(R.id.awayname)
    private val eventDate: TextView = view.find(R.id.dateevent)
    private val homeScore: TextView = view.find(R.id.homescore)
    private val awayScore: TextView = view.find(R.id.awayscore)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
//        Picasso.get().load(matchs.teamBadge).into(teamBadge)
        homeTeam.text = favorite.homeTeamName
        awayTeam.text = favorite.awayTeamName
        eventDate.text = favorite.matchDate
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore

        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}