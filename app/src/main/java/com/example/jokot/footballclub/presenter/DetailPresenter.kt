package com.example.jokot.footballclub.presenter

import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.api.ApiRepository.TheSportDBApi
import com.example.jokot.footballclub.model.TeamResponse
import com.example.jokot.footballclub.view.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val viewMatch: MatchDetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {
    fun getTeamBadge(homeId: String, awayId: String) {
        viewMatch.showLoading()
        async(UI) {
            val data1 = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsBadge(homeId)),
                        TeamResponse::class.java)
            }

            val data2 = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsBadge(awayId)),
                        TeamResponse::class.java)
            }
            viewMatch.hideLoading()
            viewMatch.showHomeBadge(data1.await().teams)
            viewMatch.showAwayBadge(data2.await().teams)


        }

    }

}