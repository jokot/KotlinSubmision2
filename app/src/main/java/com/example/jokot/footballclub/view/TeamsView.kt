package com.example.jokot.footballclub.view

import com.example.jokot.footballclub.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}