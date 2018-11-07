package com.example.jokot.footballclub.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
                FavoriteMatch.MATCH_DATE to TEXT,
                FavoriteMatch.MATCH_TIME to TEXT,
                FavoriteMatch.HOME_TEAM_ID to TEXT,
                FavoriteMatch.AWAY_TEAM_ID to TEXT,
                FavoriteMatch.HOME_TEAM_NAME to TEXT,
                FavoriteMatch.AWAY_TEAM_NAME to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,
                FavoriteMatch.AWAY_SCORE to TEXT,
                FavoriteMatch.HOME_DEFENSE to TEXT,
                FavoriteMatch.AWAY_DEFENSE to TEXT,
                FavoriteMatch.HOME_FORWARD to TEXT,
                FavoriteMatch.AWAY_FORWARD to TEXT,
                FavoriteMatch.HOME_GOAL_DETAIL to TEXT,
                FavoriteMatch.AWAY_GOAL_DETAIL to TEXT,
                FavoriteMatch.HOME_MID_FIELD to TEXT,
                FavoriteMatch.AWAY_MID_FIELD to TEXT,
                FavoriteMatch.HOME_GOAL_KEEPER to TEXT,
                FavoriteMatch.AWAY_GOAL_KEEPER to TEXT,
                FavoriteMatch.HOME_SHOTS to TEXT,
                FavoriteMatch.AWAY_SHOTS to TEXT,
                FavoriteMatch.HOME_SUBTITUTES to TEXT,
                FavoriteMatch.AWAY_SUBTITUTES to TEXT
        )

        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.TEAM_ID to TEXT + UNIQUE,
                Favorite.TEAM_NAME to TEXT,
                Favorite.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

//val Context.databaseTeam: MyDatabaseOpenHelper
//    get() = MyDatabaseOpenHelper.getInstance(applicationContext)