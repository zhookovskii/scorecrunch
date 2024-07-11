package com.zhukovskii.scorecrunch.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhukovskii.scorecrunch.databinding.CompetitionBinding
import com.zhukovskii.scorecrunch.db.CompetitionEntity
import com.zhukovskii.scorecrunch.repository.CompetitionMatches
import com.zhukovskii.scorecrunch.util.ImageLoader
import com.zhukovskii.scorecrunch.viewmodel.ScoreCrunchViewModel

class CompetitionListAdapter(
    private val values: List<CompetitionMatches>,
    private val context: Context,
    private val fragment: Fragment,
    private val viewModel: ScoreCrunchViewModel
) : RecyclerView.Adapter<CompetitionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CompetitionBinding.inflate(
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
        binding: CompetitionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val nameView: TextView = binding.competitionName
        private val iconView: ImageView = binding.competitionIcon
        private val matchRecyclerView: RecyclerView = binding.matchRecyclerview

        fun bind(competitionMatches: CompetitionMatches) {

            val competition = competitionMatches.competition
            val matches = competitionMatches.matches

            nameView.text = competition.name
            ImageLoader.load(context, competition.emblem, iconView)

            with(matchRecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = MatchListAdapter(
                    matches,
                    context,
                    fragment,
                    viewModel
                )
            }

            nameView.setOnClickListener {
                viewModel.isCollapsedCompetition[competition.id] =
                    (viewModel.isCollapsedCompetition[competition.id] ?: false).not()
                drawMatchList(competition)
            }

            drawMatchList(competition)
        }

        private fun drawMatchList(competition: CompetitionEntity) {
            if (viewModel.isCollapsedCompetition[competition.id] == true) {
                matchRecyclerView.visibility = View.GONE
            } else {
                matchRecyclerView.visibility = View.VISIBLE
            }
        }
    }
}