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
import android.widget.*
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.activity.MatchDetailActivity
import com.example.jokot.footballclub.adapter.MatchAdapter
import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.model.Match
import com.example.jokot.footballclub.presenter.MatchPresenter
import com.example.jokot.footballclub.util.invisible
import com.example.jokot.footballclub.util.visible
import com.example.jokot.footballclub.view.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment : Fragment(), AnkoComponent<Context>, MatchView {


    private var matchs: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var eventName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.event)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MatchAdapter(ctx, matchs) {
            startActivity<MatchDetailActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId,
                    "awayId" to it.awayId,
                    "homeName" to it.homeTeam,
                    "awayName" to it.awayTeam,
                    "homeScore" to it.homeScore,
                    "awayScore" to it.awayScore,
                    "dateMatch" to it.dateMatch,
                    "timeMatch" to it.timeMatch,
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
                    "awaySubtitutes" to it.awaySubtitutes)
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                eventName = spinner.selectedItem.toString()
                presenter.getMatch(eventName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getMatch(eventName)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.list_next_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matchs.clear()
        matchs.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(ctx))
    }


}
