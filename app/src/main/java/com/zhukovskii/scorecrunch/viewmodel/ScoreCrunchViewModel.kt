package com.zhukovskii.scorecrunch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhukovskii.scorecrunch.db.CompetitionEntity
import com.zhukovskii.scorecrunch.db.MatchEntity
import com.zhukovskii.scorecrunch.repository.CompetitionMatches
import com.zhukovskii.scorecrunch.repository.ScoreCrunchRepository
import com.zhukovskii.scorecrunch.util.HttpCodeTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ScoreCrunchViewModel @Inject constructor(
    private val repository: ScoreCrunchRepository
) : ViewModel() {

    val isCollapsedCompetition: MutableMap<Int, Boolean> = mutableMapOf()

    var currentMatchId = 0

    private val _stateFlow: MutableStateFlow<List<CompetitionMatches>> =
        MutableStateFlow(emptyList())

    val stateFlow: StateFlow<List<CompetitionMatches>> = _stateFlow.asStateFlow()

    private val _errorFlow: MutableSharedFlow<String> = MutableSharedFlow()

    val errorFlow: SharedFlow<String> = _errorFlow.asSharedFlow()

    init { retrieveData() }

    fun getCurrentMatch(): MatchEntity {
        return _stateFlow.value
            .flatMap { it.matches }
            .find { it.id == currentMatchId }!!
    }

    fun getCompetition(competitionId: Int): CompetitionEntity {
        return _stateFlow.value
            .find { it.competition.id == competitionId }!!
            .competition
    }

    fun retrieveData(onFinished: (() -> Unit) = {}) {

        viewModelScope.launch {

            try {
                repository.fetchFromApi()
            } catch (exception: Exception) {
                val message = when (exception) {
                    is HttpException -> HttpCodeTranslator.codeToText[exception.code()]
                    else -> exception.message
                }
                _errorFlow.emit(
                    message ?: "Unknown error occurred"
                )
            }

            val competitions = repository.retrieveFromDb()

            _stateFlow.value = competitions

            onFinished()
        }
    }
}