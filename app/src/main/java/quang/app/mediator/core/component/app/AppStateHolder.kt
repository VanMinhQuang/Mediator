package quang.app.mediator.core.component.app


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton


data class AppState(
    val test: String = "",
    val test2: String = ""
)

@Singleton
class AppStateHolder @Inject constructor() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

     fun testing(test: String, test2: String) {
        _state.update {
            it.copy(test = test, test2 = test2)
        }
    }
}
