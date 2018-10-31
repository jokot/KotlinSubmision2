package com.example.jokot.footballclub.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.R.id.add_to_favorite
import com.example.jokot.footballclub.api.ApiRepository
import com.example.jokot.footballclub.database.Favorite
import com.example.jokot.footballclub.database.database
import com.example.jokot.footballclub.model.Team
import com.example.jokot.footballclub.presenter.DetailPresenter
import com.example.jokot.footballclub.util.invisible
import com.example.jokot.footballclub.util.visible
import com.example.jokot.footballclub.view.DetailView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity(), DetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var presenter: DetailPresenter
    private lateinit var homeTeamData: Team
    private lateinit var awayTeamData: Team

    private var eventId: String = ""
    private var homeId: String = ""
    private var awayId: String = ""
    private var homeName: String = ""
    private var awayName: String = ""
    private var dateMatch: String = ""

    private var homeScore: String? = ""
    private var awayScore: String? = ""
    private var homeShot: String? = ""
    private var awayShot: String? = ""

    private var homeGoal: String? = ""
    private var awayGoal: String? = ""


    private var homeGoalKeeper: String? = ""
    private var awayGoalKeeper: String? = ""
    private var homeDefense: String? = ""
    private var awayDefense: String? = ""
    private var homeMidfield: String? = ""
    private var awayMidfield: String? = ""
    private var homeForward: String? = ""
    private var awayForward: String? = ""
    private var homeSubtitutes: String? = ""
    private var awaySubtitutes: String? = ""

    private var homeBadge: String? = ""
    private var awayBadge: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mainDetail()

        supportActionBar?.title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getTeamBadge(homeId, awayId)

        if (intent.getStringExtra("homeScore") != null) {
            extraDetail()
        }

    }

    private fun mainDetail() {
        eventId = intent.getStringExtra("eventId")
        homeId = intent.getStringExtra("homeId")
        awayId = intent.getStringExtra("awayId")

        homeName = intent.getStringExtra("homeName")
        awayName = intent.getStringExtra("awayName")
        dateMatch = intent.getStringExtra("dateMatch")

        home_name.text = homeName
        away_name.text = awayName
        event_date.text = dateMatch
    }

    private fun extraDetail() {
        homeScore = intent.getStringExtra("homeScore")
        awayScore = intent.getStringExtra("awayScore")
        homeShot = intent.getStringExtra("homeShot")
        awayShot = intent.getStringExtra("awayShot")

        homeGoal = intent.getStringExtra("homeGoal")
        awayGoal = intent.getStringExtra("awayGoal")

        homeGoalKeeper = intent.getStringExtra("homeGoalKeeper")
        awayGoalKeeper = intent.getStringExtra("awayGoalKeeper")
        homeDefense = intent.getStringExtra("homeDefense")
        awayDefense = intent.getStringExtra("awayDefense")
        homeMidfield = intent.getStringExtra("homeMidfield")
        awayMidfield = intent.getStringExtra("awayMidfield")
        homeForward = intent.getStringExtra("homeForward")
        awayForward = intent.getStringExtra("awayForward")
        homeSubtitutes = intent.getStringExtra("homeSubtitutes")
        awaySubtitutes = intent.getStringExtra("awaySubtitutes")

        home_score.text = homeScore
        away_score.text = awayScore
        home_shots.text = homeShot
        away_shots.text = awayShot

        home_goals.text = homeGoal
        away_goals.text = awayGoal

        home_goalkeeper.text = homeGoalKeeper
        away_goalkeeper.text = awayGoalKeeper
        home_defence.text = homeDefense
        away_defence.text = awayDefense
        home_midfield.text = homeMidfield
        away_midfield.text = awayMidfield
        home_forward.text = homeForward
        away_forward.text = awayForward
        home_subtituetes.text = homeSubtitutes
        away_subtittes.text = awaySubtitutes
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showHomeBadge(data: List<Team>) {
        homeTeamData = data[0]
        homeBadge = homeTeamData.teamBadge
        Glide.with(this).load(homeBadge).into(home_image)
    }

    override fun showAwayBadge(data: List<Team>) {
        awayTeamData = data[0]
        awayBadge = awayTeamData.teamBadge
        Glide.with(this).load(awayBadge).into(away_image)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(MATCH_ID = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true

            setFavorite()
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.MATCH_ID to eventId,
                        Favorite.MATCH_DATE to dateMatch,
                        Favorite.HOME_TEAM_ID to homeId,
                        Favorite.AWAY_TEAM_ID to awayId,
                        Favorite.HOME_TEAM_NAME to homeName,
                        Favorite.AWAY_TEAM_NAME to awayName,
                        Favorite.HOME_SCORE to homeScore,
                        Favorite.AWAY_SCORE to awayScore,
                        Favorite.HOME_DEFENSE to homeDefense,
                        Favorite.AWAY_DEFENSE to awayDefense,
                        Favorite.HOME_FORWARD to homeForward,
                        Favorite.AWAY_FORWARD to awayForward,
                        Favorite.HOME_GOAL_DETAIL to homeGoal,
                        Favorite.AWAY_GOAL_DETAIL to awayGoal,
                        Favorite.HOME_MID_FIELD to homeMidfield,
                        Favorite.AWAY_MID_FIELD to awayMidfield,
                        Favorite.HOME_GOAL_KEEPER to homeGoalKeeper,
                        Favorite.AWAY_GOAL_KEEPER to awayGoalKeeper,
                        Favorite.HOME_SHOTS to homeShot,
                        Favorite.AWAY_SHOTS to awayShot,
                        Favorite.HOME_SUBTITUTES to homeSubtitutes,
                        Favorite.AWAY_SUBTITUTES to awaySubtitutes
                )
            }
            snackbar(relative_layout, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(relative_layout, e.localizedMessage).show()
        }
    }


    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to eventId)
            }
            Snackbar.make(relative_layout, "Remove From Favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(relative_layout, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
    }
}
