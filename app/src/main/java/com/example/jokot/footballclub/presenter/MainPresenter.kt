package com.example.jokot.footballclub.presenter

import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.model.MatchResponse
import com.example.jokot.footballclub.util.CoroutineContextProvider
import com.example.jokot.footballclub.view.MainView
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MainPresenter(private val view: MainView,
                    private var apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatch() {
        view.showLoading()

        val match = "eventsnextleague"

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiRepository.TheSportDBApi.getNextMatchs(match)),
                        MatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

    fun getPrevMath() {
        view.showLoading()

        val match = "eventspastleague"

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiRepository.TheSportDBApi.getPrevMatch(match)),
                        MatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()

        }
    }
}