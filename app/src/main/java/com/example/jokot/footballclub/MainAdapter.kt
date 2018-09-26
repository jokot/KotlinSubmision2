package com.example.jokot.footballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jokot.footballclub.R.id.away_team
import com.example.jokot.footballclub.R.id.home_team
import org.jetbrains.anko.*

class MainAdapter(private val context: Context, private val matchs: List<Match>,  val listener: (Match) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(matchs[position],listener)
    }

    override fun getItemCount(): Int = matchs.size

}


class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

//    private val teamBadge: ImageView = view.find(team_badge)
    private val homeTeam: TextView = view.find(home_team)
    private val awayTeam: TextView = view.find(away_team)

    fun bindItem(matchs: Match,listener: (Match) -> Unit) {
//        Picasso.get().load(matchs.teamBadge).into(teamBadge)
        homeTeam.text = matchs.homeTeam
        awayTeam.text = matchs.awayTeam
        itemView.setOnClickListener {
            listener(matchs)
        }
    }
}