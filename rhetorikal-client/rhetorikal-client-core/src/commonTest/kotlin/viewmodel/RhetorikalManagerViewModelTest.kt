package viewmodel

import expect.expect
import expect.toContainElements
import rhetorikal.RhetoricianParams
import rhetorikal.services.RhetorikalClientService
import rhetorikal.services.RhetorikalClientServiceKtor
import rhetorikal.viewmodels.RhetorikalManagerViewModel
import rhetorikal.viewmodels.RhetorikalManagerViewModel.Intent
import rhetorikal.viewmodels.RhetorikalManagerViewModel.State
import test.asyncTest
import kotlin.test.Test

class RhetorikalManagerViewModelTest {
    val service: RhetorikalClientService = RhetorikalClientServiceKtor(
        endpoint = "http://localhost:3000"
    )
    val vm = RhetorikalManagerViewModel(service)

    @Test
    fun should_fetch_witnesses() = asyncTest {
        vm.test(Intent.LoadWitnesses)
        val state = expect(vm).toBeIn<State.Witnesses>()
        println(state.data)
    }

    @Test
    fun should_be_able_to_create_a_witness() = asyncTest {
        val params = RhetoricianParams(
            name = "Justin",
            title = "Dev",
            workplace = "aSoft"
        )
        vm.test(Intent.CreateWitness(params))
        expect(vm).toBeIn<State.Success>()
        expect(vm).toBeIn(State.Success("Successfully created Justin"))
    }
}