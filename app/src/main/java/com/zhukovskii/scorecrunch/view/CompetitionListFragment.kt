package com.zhukovskii.scorecrunch.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zhukovskii.scorecrunch.R
import com.zhukovskii.scorecrunch.recycler.CompetitionListAdapter
import com.zhukovskii.scorecrunch.repository.CompetitionMatches
import com.zhukovskii.scorecrunch.viewmodel.ScoreCrunchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CompetitionListFragment : Fragment() {

    private val viewModel: ScoreCrunchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_competition_list, container, false)

        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        swipeLayout.isRefreshing = true

        swipeLayout.setOnRefreshListener {
            viewModel.retrieveData {
                swipeLayout.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {

                launch {
                    viewModel.stateFlow.collectLatest {
                        populateRecyclerView(
                            this@CompetitionListFragment,
                            view,
                            it
                        )
                        swipeLayout.isRefreshing = false
                    }
                }

                launch {
                    viewModel.errorFlow.collectLatest {
                        makeToast(requireContext(), it)
                    }
                    swipeLayout.isRefreshing = false
                }
            }
        }

        return view
    }

    private fun populateRecyclerView(
        fragment: Fragment,
        view: View,
        competitions: List<CompetitionMatches>
    ) {
        with(
            view.findViewById<RecyclerView>(R.id.competition_recyclerview)
        ) {
            layoutManager = LinearLayoutManager(context)

            adapter = CompetitionListAdapter(
                competitions,
                context,
                fragment,
                viewModel
            )
        }
    }

    private fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}