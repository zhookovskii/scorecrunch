package com.zhukovskii.scorecrunch.navigation

import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.zhukovskii.scorecrunch.R

object ScoreCrunchNavigator {

    private fun getNavHostFragment(supportFragmentManager: FragmentManager): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    fun splashToList(supportFragmentManager: FragmentManager) {
        getNavHostFragment(supportFragmentManager)
            .navController
            .navigate(R.id.action_splash_to_competition_list)
    }

    fun listToDetails(supportFragmentManager: FragmentManager) {
        getNavHostFragment(supportFragmentManager)
            .navController
            .navigate(R.id.action_competition_list_to_match_details)
    }
}