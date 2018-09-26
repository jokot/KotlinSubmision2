package com.example.jokot.footballclub

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}