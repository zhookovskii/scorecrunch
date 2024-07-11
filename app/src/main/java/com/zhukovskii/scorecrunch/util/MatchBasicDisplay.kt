package com.zhukovskii.scorecrunch.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.zhukovskii.scorecrunch.R
import com.zhukovskii.scorecrunch.db.MatchEntity

data class MatchBasicDisplay(
    val homeTeamNameView: TextView,
    val homeTeamCrestView: ImageView,
    val awayTeamNameView: TextView,
    val awayTeamCrestView: ImageView,
    val scoreView: TextView,
    val dateView: TextView,
    val liveView: TextView,
    val statusView: TextView
) {
    fun bind(context: Context, match: MatchEntity) {
        homeTeamNameView.text = match.homeTeamName
        awayTeamNameView.text = match.awayTeamName
        ImageLoader.load(context, match.homeTeamCrest, homeTeamCrestView)
        ImageLoader.load(context, match.awayTeamCrest, awayTeamCrestView)

        val scoreString by lazy {
            context.getString(
                R.string.score_placeholder,
                match.scoreHome,
                match.scoreAway
            )
        }

        when (match.status) {
            "TIMED", "SCHEDULED", "LIVE" -> {
                scoreView.hide()
                liveView.hide()
                statusView.hide()
                dateView.text = match.utcDate.formatDate()
            }
            "IN_PLAY" -> {
                dateView.hide()
                statusView.hide()
                scoreView.text = scoreString
            }
            "PAUSED" -> {
                dateView.hide()
                liveView.hide()
                scoreView.text = scoreString
                statusView.text = context.getString(R.string.paused_text)
            }
            "FINISHED" -> {
                dateView.hide()
                liveView.hide()
                scoreView.text = scoreString
                statusView.text = APITermResolver.resolve(match.scoreDuration)
            }
            "POSTPONED", "SUSPENDED", "CANCELLED" -> {
                dateView.hide()
                liveView.hide()
                scoreView.hide()
                statusView.text = APITermResolver.resolve(match.status)
            }
        }
    }
}