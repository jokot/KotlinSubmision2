package com.example.jokot.footballclub.presenter

import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.model.MatchResponse
import com.example.jokot.footballclub.util.CoroutineContextProvider
import com.example.jokot.footballclub.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private var apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatch(match:String?) {
        async(context.main) {
            var event = ""
            if (match == "Next Match"){
                event= "eventsnextleague"
            }else{
                event = "eventspastleague"
            }

            view.showLoading()
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiRepository.TheSportDBApi.getMatchs(event)),
                        MatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }
}