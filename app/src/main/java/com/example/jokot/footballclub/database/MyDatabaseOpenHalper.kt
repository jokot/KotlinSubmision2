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
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.MATCH_ID to TEXT + UNIQUE,
                Favorite.MATCH_DATE to TEXT,
                Favorite.HOME_TEAM_ID to TEXT,
                Favorite.AWAY_TEAM_ID to TEXT,
                Favorite.HOME_TEAM_NAME to TEXT,
                Favorite.AWAY_TEAM_NAME to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.AWAY_SCORE to TEXT,
                Favorite.HOME_DEFENSE to TEXT,
                Favorite.AWAY_DEFENSE to TEXT,
                Favorite.HOME_FORWARD to TEXT,
                Favorite.AWAY_FORWARD to TEXT,
                Favorite.HOME_GOAL_DETAIL to TEXT,
                Favorite.AWAY_GOAL_DETAIL to TEXT,
                Favorite.HOME_MID_FIELD to TEXT,
                Favorite.AWAY_MID_FIELD to TEXT,
                Favorite.HOME_GOAL_KEEPER to TEXT,
                Favorite.AWAY_GOAL_KEEPER to TEXT,
                Favorite.HOME_SHOTS to TEXT,
                Favorite.AWAY_SHOTS to TEXT,
                Favorite.HOME_SUBTITUTES to TEXT,
                Favorite.AWAY_SUBTITUTES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)