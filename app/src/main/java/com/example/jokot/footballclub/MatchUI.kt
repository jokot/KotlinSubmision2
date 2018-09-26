package com.example.jokot.footballclub

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
//            linearLayout {
//                lparams(width = matchParent, height = wrapContent)
//                padding = dip(16)
//                orientation = LinearLayout.HORIZONTAL
//
////                imageView {
////                    id = R.id.team_badge
////                }.lparams{
////                    height = dip(50)
////                    width = dip(50)
////                }
//
//                textView {
//                    id = R.id.home_name
//                    textSize = 16f
//                }.lparams{
//                    margin = dip(15)
//                }
//
//                textView(){
//                    id = R.id.away_name
//                }.lparams{
//
//                }
//
//            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)
                gravity = Gravity.CENTER
                textView {
                    text = "Min,16 Januari 2019"
                    id=R.id.date_match
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    textView {
                        text = "Mancester United"
                        id = R.id.home_team
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1f
                    }
                    textView {
                        text = "0"
                    }.lparams {
                        margin = dip(3)
                    }
                    textView {
                        text = "VS"
                    }.lparams {
                        margin = dip(5)
                    }
                    textView {
                        text = "0"
                    }.lparams {
                        margin = dip(3)
                    }
                    textView {
                        text = "Mancester United"
                        id = R.id.away_team
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1f
                    }
                }.lparams(width = matchParent)
            }
        }
    }

}
