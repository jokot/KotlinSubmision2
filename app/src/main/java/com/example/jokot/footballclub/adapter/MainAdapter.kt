package com.example.jokot.footballclub.adapter

//import com.example.jokot.footballclub.R.id.away_team
//import com.example.jokot.footballclub.R.id.home_team
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.R.id.*
import com.example.jokot.footballclub.model.Match
import org.jetbrains.anko.find

class MainAdapter(private val context: Context, private val matchs: List<Match>, val listener: (Match) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }

    override fun getItemCount(): Int = matchs.size

}


class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //    private val teamBadge: ImageView = view.find(team_badge)
    private val homeTeam: TextView = view.find(homename)
    private val awayTeam: TextView = view.find(awayname)
    private val eventDate: TextView = view.find(dateevent)
    private val homeScore: TextView = view.find(homescore)
    private val awayScore: TextView = view.find(awayscore)

    fun bindItem(matchs: Match, listener: (Match) -> Unit) {
//        Picasso.get().load(matchs.teamBadge).into(teamBadge)
        homeTeam.text = matchs.homeTeam
        awayTeam.text = matchs.awayTeam
        eventDate.text = matchs.dateMatch
        homeScore.text = matchs.homeScore
        awayScore.text = matchs.awayScore

        itemView.setOnClickListener {
            listener(matchs)
        }
    }
}