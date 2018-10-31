package com.example.jokot.footballclub.presenter

import com.example.jokot.footballclub.TestContextProvider
import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.model.Match
import com.example.jokot.footballclub.model.MatchResponse
import com.example.jokot.footballclub.model.Team
import com.example.jokot.footballclub.model.TeamResponse
import com.example.jokot.footballclub.view.MainView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    private
    lateinit var view :MainView

    @Mock
    private
    lateinit var gson :Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private  lateinit var presenter: MainPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view,apiRepository,gson,TestContextProvider())
    }

    @Test
    fun getNextMatch() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val event = "eventsnextleague"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getNextMatchs(event)),
                        MatchResponse::class.java
        )).thenReturn(response)

        presenter.getNextMatch()

        verify(view).showLoading()
        verify(view).showMatchList(match)
        verify(view).hideLoading()

    }

    @Test
    fun getPrevMath() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val event = "eventspastleague"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPrevMatch(event)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getPrevMath()

        verify(view).showLoading()
        verify(view).showMatchList(match)
        verify(view).hideLoading()

    }
}