package quang.app.mediator.features.main.meditate.viewmodel

import quang.app.mediator.domain.model.MeditationModel
import quang.app.mediator.features.main.meditate.view.MeditateMenuItem

data class MeditateState(
    val selectedType: MeditateMenuItem? = null,
    val meditationsByType: Map<MeditateMenuItem, List<MeditationModel>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)
