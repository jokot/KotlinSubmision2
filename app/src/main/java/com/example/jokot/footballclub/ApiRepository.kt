package com.example.jokot.footballclub

import android.net.Uri
import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String{
        return URL(url).readText()
    }

    object TheSportDBApi{
        fun getMatchs(event:String?):String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("eventspastleague.php")
                    .appendQueryParameter("id","4328")
                    .build()
                    .toString()
        }
    }


}
fun main(args: Array<String>){
    println(ApiRepository.TheSportDBApi.getMatchs("hai"))
}