package rhetorikal.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import later.await
import rhetorikal.Rhetorician
import rhetorikal.RhetoricianParams
import rhetorikal.services.RhetorikalClientService
import rhetorikal.viewmodels.RhetorikalManagerViewModel.Intent
import rhetorikal.viewmodels.RhetorikalManagerViewModel.State
import viewmodel.ViewModel

class RhetorikalManagerViewModel(
    val service: RhetorikalClientService
) : ViewModel<Intent, State>(State.Loading("Preparing Rhetorikal")) {
    sealed class State {
        data class Loading(val msg: String) : State()
        data class Success(val msg: String) : State()
        data class Witnesses(val data: List<Rhetorician>) : State()
        data class WitnessForm(val witness: Rhetorician?) : State()
        data class Failure(val error: Throwable) : State()
    }

    sealed class Intent {
        object LoadWitnesses : Intent()
        data class CreateWitness(val params: RhetoricianParams) : Intent()
        data class WitnessForm(val witness: Rhetorician?) : Intent()
        data class EditWitness(val witness: Rhetorician) : Intent()
    }

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        is Intent.LoadWitnesses -> loadWitness()
        is Intent.CreateWitness -> createWitness(i)
        is Intent.WitnessForm -> ui.value = State.WitnessForm(i.witness)
        is Intent.EditWitness -> editWitness()
    }

    private fun editWitness() {
        ui.value = State.Failure(Throwable("No Witness editing for you today"))
    }

    private fun CoroutineScope.createWitness(i: Intent.CreateWitness) = launch {
        ui.value = State.Loading("Creating ${i.params.name} as a witness")
        val witness = service.rhetoricians.create(i.params).await()
        ui.value = State.Success("Successfully created ${witness.name}")
    }

    private fun CoroutineScope.loadWitness() = launch {
        ui.value = State.Loading("Loading witnesses")
        val data = service.rhetoricians.all().await()
        ui.value = State.Witnesses(data)
    }
}