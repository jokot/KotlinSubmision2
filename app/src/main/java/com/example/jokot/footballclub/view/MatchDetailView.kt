package com.example.jokot.footballclub.view

import com.example.jokot.footballclub.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showHomeBadge(data: List<Team>)
    fun showAwayBadge(data: List<Team>)
}