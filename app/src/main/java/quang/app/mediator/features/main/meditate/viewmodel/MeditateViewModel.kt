package quang.app.mediator.features.main.meditate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import quang.app.mediator.domain.model.MeditationModel
import quang.app.mediator.features.main.meditate.view.MeditateMenuItem
import quang.app.mediator.features.main.meditate.view.menuMediates

class MeditateViewModel: ViewModel() {
    private val _state = MutableStateFlow(MeditateState())
    val state = _state.asStateFlow()


    fun onEvent(event: MeditateEvent) {
        when(event) {
            is MeditateEvent.SelectMeditationType -> {
                val type = event.meditationType

                // Update selected type
                _state.value = state.value.copy(selectedType = type)

                // Check if we already have data
                if (!state.value.meditationsByType.containsKey(type)) {
                    loadMeditationsForType(type)
                }
            }

            MeditateEvent.LoadMeditations -> {
                val data = menuMediates.associateWith { MeditationModel.dummyList()}
                _state.value = state.value.copy(
                    selectedType = menuMediates[0],
                    meditationsByType = data

                )
            }
        }
    }



    private fun loadMeditationsForType(type: MeditateMenuItem) {
        viewModelScope.launch {
            try {
                // Show loading
                _state.value = state.value.copy(isLoading = true, error = null)


                val meditations = MeditationModel.dummyList()


                val updatedMap = state.value.meditationsByType.toMutableMap()
                updatedMap[type] = meditations

                _state.value = state.value.copy(
                    meditationsByType = updatedMap,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

}
