package com.example.jokot.footballclub.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events") var events: List<Match>)