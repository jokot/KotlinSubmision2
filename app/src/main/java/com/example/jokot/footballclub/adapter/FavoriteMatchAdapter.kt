package com.example.jokot.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.database.FavoriteMatch
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMatchAdapter(private val context: Context, private val favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatch.size

}


class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //    private val teamBadge: ImageView = view.find(team_badge)
    private val homeTeam: TextView = view.find(R.id.homename)
    private val awayTeam: TextView = view.find(R.id.awayname)
    private val eventDate: TextView = view.find(R.id.dateevent)
    private val homeScore: TextView = view.find(R.id.homescore)
    private val awayScore: TextView = view.find(R.id.awayscore)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
//        Picasso.get().load(matchs.teamBadge).into(teamBadge)

        homeTeam.text = favoriteMatch.homeTeamName
        awayTeam.text = favoriteMatch.awayTeamName


        eventDate.text = toGMTFormat(favoriteMatch.matchDate.toString(),favoriteMatch.matchTime.toString()).toString()
//                favoriteMatch.matchDate
        homeScore.text = favoriteMatch.homeScore
        awayScore.text = favoriteMatch.awayScore

        itemView.setOnClickListener {
            listener(favoriteMatch)
        }
    }

    fun toGMTFormat(date: String, time: String): Date? {

        val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)

    }
}