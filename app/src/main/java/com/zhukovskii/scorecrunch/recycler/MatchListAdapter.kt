package com.zhukovskii.scorecrunch.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zhukovskii.scorecrunch.databinding.MatchBinding
import com.zhukovskii.scorecrunch.db.MatchEntity
import com.zhukovskii.scorecrunch.navigation.ScoreCrunchNavigator
import com.zhukovskii.scorecrunch.util.MatchBasicDisplay
import com.zhukovskii.scorecrunch.viewmodel.ScoreCrunchViewModel

class MatchListAdapter(
    private val values: List<MatchEntity>,
    private val context: Context,
    private val fragment: Fragment,
    private val viewModel: ScoreCrunchViewModel
) : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            MatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(
        private val binding: MatchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val homeTeamNameView: TextView = binding.homeTeamName
        private val homeTeamCrestView: ImageView = binding.homeTeamCrest
        private val awayTeamNameView: TextView = binding.awayTeamName
        private val awayTeamCrestView: ImageView = binding.awayTeamCrest
        private val scoreView: TextView = binding.score
        private val dateView: TextView = binding.date
        private val liveView: TextView = binding.liveText
        private val statusView: TextView = binding.matchStatus

        fun bind(match: MatchEntity) {

            MatchBasicDisplay(
                homeTeamNameView,
                homeTeamCrestView,
                awayTeamNameView,
                awayTeamCrestView,
                scoreView,
                dateView,
                liveView,
                statusView
            ).bind(context, match)

            binding.root.setOnClickListener {
                viewModel.currentMatchId = match.id

                fragment.activity?.let {
                    ScoreCrunchNavigator.listToDetails(
                        it.supportFragmentManager
                    )
                }
            }
        }
    }
}