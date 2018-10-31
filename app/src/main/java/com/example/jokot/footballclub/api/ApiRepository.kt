package com.example.jokot.footballclub.api

import android.net.Uri
import com.example.jokot.footballclub.BuildConfig
import java.net.URL

class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }

    object TheSportDBApi {
        fun getNextMatchs(event: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$event.php?id=" + "4328"
        }

        fun getPrevMatch(event: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$event.php?id=" + "4328"

        }

        fun getTeamsBadge(team: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + "$team"
        }
    }
}
