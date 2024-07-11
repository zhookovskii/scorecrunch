package com.zhukovskii.scorecrunch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zhukovskii.scorecrunch.R
import com.zhukovskii.scorecrunch.util.APITermResolver
import com.zhukovskii.scorecrunch.util.ImageLoader
import com.zhukovskii.scorecrunch.util.MatchBasicDisplay
import com.zhukovskii.scorecrunch.util.hide
import com.zhukovskii.scorecrunch.viewmodel.ScoreCrunchViewModel

class MatchDetailsFragment : Fragment() {

    private val viewModel: ScoreCrunchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_details, container, false)

        val match = viewModel.getCurrentMatch()
        val competition = viewModel.getCompetition(match.competitionId)

        val homeTeamNameView: TextView = view.findViewById(R.id.home_team_name)
        val homeTeamCrestView: ImageView = view.findViewById(R.id.home_team_crest)
        val awayTeamNameView: TextView = view.findViewById(R.id.away_team_name)
        val awayTeamCrestView: ImageView = view.findViewById(R.id.away_team_crest)
        val scoreView: TextView = view.findViewById(R.id.score)
        val dateView: TextView = view.findViewById(R.id.date)
        val liveView: TextView = view.findViewById(R.id.live_text)
        val statusView: TextView = view.findViewById(R.id.match_status)
        val competitionNameView: TextView = view.findViewById(R.id.competition_name)
        val competitionIconView: ImageView = view.findViewById(R.id.competition_icon)
        val stageView: TextView = view.findViewById(R.id.stage)
        val matchResultView: TextView = view.findViewById(R.id.match_result)

        MatchBasicDisplay(
            homeTeamNameView,
            homeTeamCrestView,
            awayTeamNameView,
            awayTeamCrestView,
            scoreView,
            dateView,
            liveView,
            statusView
        ).bind(requireContext(), match)

        competitionNameView.text = competition.name
        ImageLoader.load(requireContext(), competition.emblem, competitionIconView)

        stageView.text = APITermResolver.resolve(match.stage)

        if (match.status != "FINISHED") {
            matchResultView.hide()
        } else {
            val duration = when (match.scoreDuration) {
                "REGULAR" -> "after 90 minutes"
                "EXTRA_TIME" -> "after extra time"
                "PENALTY_SHOOTOUT" -> "after penalty shootout"
                else -> ""
            }
            val winner = when (match.scoreWinner) {
                "HOME_TEAM" -> match.homeTeamName
                "AWAY_TEAM" -> match.awayTeamName
                else -> "Nobody"
            }
            matchResultView.text = requireContext().getString(
                R.string.match_result_placeholder,
                winner,
                duration
            )
        }

        return view
    }
}