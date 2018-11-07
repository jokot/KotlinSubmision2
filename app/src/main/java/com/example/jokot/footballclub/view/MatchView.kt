package com.example.jokot.footballclub.view

import com.example.jokot.footballclub.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}