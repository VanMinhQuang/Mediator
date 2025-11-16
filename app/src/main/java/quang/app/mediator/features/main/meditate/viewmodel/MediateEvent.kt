package quang.app.mediator.features.main.meditate.viewmodel

import quang.app.mediator.features.main.meditate.view.MeditateMenuItem

sealed class MeditateEvent {
    data class SelectMeditationType(val meditationType: MeditateMenuItem) : MeditateEvent()
    object LoadMeditations : MeditateEvent()
}


sealed class MeditateUIEvent {
    data class ShowError(val message: String) : MeditateUIEvent()
    data class ShowSuccess(val message: String) : MeditateUIEvent()


}
