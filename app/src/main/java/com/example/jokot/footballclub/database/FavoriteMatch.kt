package com.example.jokot.footballclub.database

data class FavoriteMatch(
        val id: Long?,
        val matchId: String?,
        val matchDate: String?,
        val matchTime: String?,
        val homeTeamId: String?,
        val awayTeamId: String?,
        val homeTeamName: String?,
        val awayTeamName: String?,
        val homeScore: String?,
        val awayScore: String?,
        val homeGoalDetails: String?,
        val awayGoalDetails: String?,
        val homeShots: String?,
        val awayShots: String?,
        val homeGoalKeeper: String?,
        val awayGoalKeeper: String?,
        val homeDefense: String?,
        val awayDefense: String?,
        val homeMidfield: String?,
        val awayMidfield: String?,
        val homeForward: String?,
        val awayForward: String?,
        val homeSubtitutes: String?,
        val awaySubtitutes: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val HOME_MID_FIELD: String = "HOME_MID_FIELD"
        const val AWAY_MID_FIELD: String = "AWAY_MID_FIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
        const val HOME_SUBTITUTES: String = "HOME_SUBTITUTES"
        const val AWAY_SUBTITUTES: String = "AWAY_SUBTITUTES"
    }
}