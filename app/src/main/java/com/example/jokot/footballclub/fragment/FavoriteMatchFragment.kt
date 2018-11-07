package com.example.jokot.footballclub.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.R.color.colorAccent
import com.example.jokot.footballclub.activity.MatchDetailActivity
import com.example.jokot.footballclub.adapter.FavoriteMatchAdapter
import com.example.jokot.footballclub.database.FavoriteMatch
import com.example.jokot.footballclub.database.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class FavoriteMatchFragment : Fragment(), AnkoComponent<Context> {

    private var favoriteMatches: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(ctx, favoriteMatches) {
            startActivity<MatchDetailActivity>(
                    "eventId" to it.matchId,
                    "homeId" to it.homeTeamId,
                    "awayId" to it.awayTeamId,
                    "homeName" to it.homeTeamName,
                    "awayName" to it.awayTeamName,
                    "homeScore" to it.homeScore,
                    "awayScore" to it.awayScore,
                    "dateMatch" to it.matchDate,
                    "timeMatch" to it.matchTime,
                    "homeGoal" to it.homeGoalDetails,
                    "awayGoal" to it.awayGoalDetails,
                    "homeShot" to it.homeShots.toString(),
                    "awayShot" to it.awayShots.toString(),
                    "homeGoalKeeper" to it.homeGoalKeeper,
                    "awayGoalKeeper" to it.awayGoalKeeper,
                    "homeDefense" to it.homeDefense,
                    "awayDefense" to it.awayDefense,
                    "homeMidfield" to it.homeMidfield,
                    "awayMidfield" to it.awayMidfield,
                    "homeForward" to it.homeForward,
                    "awayForward" to it.awayForward,
                    "homeSubtitutes" to it.homeSubtitutes,
                    "awaySubtitutes" to it.awaySubtitutes
            )
        }
        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favoriteMatches.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteMatches.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    id = R.id.list_favorite
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
