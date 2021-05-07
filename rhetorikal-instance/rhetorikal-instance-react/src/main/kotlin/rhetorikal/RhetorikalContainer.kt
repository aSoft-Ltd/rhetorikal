package rhetorikal

import kotlinext.js.jsObject
import live.Watcher
import react.RBuilder
import react.RProps
import reakt.ErrorBox
import reakt.LoadingBox
import reakt.SuccessBox
import rhetorikal.services.RhetorikalClientServiceKtor
import rhetorikal.viewmodels.RhetorikalManagerViewModel
import rhetorikal.viewmodels.RhetorikalManagerViewModel.Intent
import rhetorikal.viewmodels.RhetorikalManagerViewModel.State
import rhetorikal.witenesses.WitnessForm
import rhetorikal.witenesses.WitnessessList
import viewmodel.VComponent

@JsExport
class RhetorikalContainer private constructor() : VComponent<RProps, Intent, State, RhetorikalManagerViewModel>() {
    override val viewModel by lazy {
        val service = RhetorikalClientServiceKtor("http://localhost:3000")
        RhetorikalManagerViewModel(service)
    }

    private var listener: Watcher<State>? = null

    override fun componentDidMount() {
        listener = viewModel.ui.watch {
            setState(UIState(it))
        }
        post(Intent.LoadWitnesses)
    }

    override fun componentWillUnmount() {
        listener?.stop()
        listener = null
    }

    override fun RBuilder.render(ui: State): Any = when (ui) {
        is State.Loading -> LoadingBox(title = ui.msg)
        is State.Success -> SuccessBox(title = ui.msg)
        is State.Witnesses -> WitnessessList(
            data = ui.data,
            onCreateWitnessButtonClicked = { post(Intent.WitnessForm(witness = null)) },
            onEditWitnessButtonClicked = { post(Intent.WitnessForm(witness = it)) }
        )
        is State.WitnessForm -> WitnessForm(
            witness = ui.witness,
            onWitnessCreated = { post(Intent.CreateWitness(it)) },
            onWitnessEdited = { post(Intent.EditWitness(it)) }
        )
        is State.Failure -> ErrorBox(ui.error)
    }
}

fun RBuilder.RhetorikalContainer() = child(RhetorikalContainer::class.js, jsObject()) {}