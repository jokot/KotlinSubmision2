package com.example.jokot.footballclub

import com.google.gson.annotations.SerializedName

data class Match(
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String?=null,

        @SerializedName("intAwayScore")
        var awayScore: String? =null,

        @SerializedName("strDate")
        var dateMatch: String? = null
)