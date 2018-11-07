package com.example.jokot.footballclub.presenter

import com.example.jokot.footballclub.TestContextProvider
import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.model.Match
import com.example.jokot.footballclub.model.MatchResponse
import com.example.jokot.footballclub.view.MatchView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatch() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val event = "eventsnextleague"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getMatchs(event)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatch(event)

        verify(view).showLoading()
        verify(view).showMatchList(match)
        verify(view).hideLoading()

    }

}