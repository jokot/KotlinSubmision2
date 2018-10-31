package com.example.jokot.footballclub.model


import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)