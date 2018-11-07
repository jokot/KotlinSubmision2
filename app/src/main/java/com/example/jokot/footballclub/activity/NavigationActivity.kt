package com.example.jokot.footballclub.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.fragment.FavoriteFragment
import com.example.jokot.footballclub.fragment.MatchFragment
import com.example.jokot.footballclub.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                changeFragment(MatchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                changeFragment(TeamsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                changeFragment(FavoriteFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MatchFragment())
                .commit()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
