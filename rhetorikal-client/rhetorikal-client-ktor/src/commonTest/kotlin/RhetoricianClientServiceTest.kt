import expect.expect
import expect.expectFailure
import expect.toBe
import later.await
import test.asyncTest
import rhetorikal.Rhetorician
import rhetorikal.services.RhetoriciansClientServiceKtor
import rhetorikal.RhetoricianParams
import kotlin.test.Test

class RhetoricianClientServiceTest {
    private val service = RhetoriciansClientServiceKtor(endpoint = ENDPOINT)

    @Test
    fun should_fetch_all_witnesses() = asyncTest {
        val witnesses = service.all().await()
        println(witnesses)
    }

    @Test
    fun should_create_a_witness_and_should_exist_in_the_service() = asyncTest {
        val params = RhetoricianParams(
            name = "Test Witness",
            title = "Test title",
            workplace = "Test Workplace"
        )
        val witness = service.create(params).await()
        println(witness)
        expect(witness.name).toBe(params.name)
        should_fetch_all_witnesses()
    }

    @Test
    fun should_create_and_edit_a_witness() = asyncTest {
        val info = RhetoricianParams(
            name = "Another Test Witness ",
            title = "Another Test title",
            workplace = "Another Test Workplace"
        )
        val witness = service.create(info).await()
        println(witness)
        expect(witness.name).toBe(info.name)
        val newName = "Edited Test Witness"
        val newWitness = service.edit(
            witness.copy(
                name = newName
            )
        ).await()
        expect(newWitness.name).toBe(newName)
        should_fetch_all_witnesses()
    }

    @Test
    fun should_fail_to_edit_a_non_existing_witness() = asyncTest {
        expectFailure {
            service.edit(
                Rhetorician(
                    uid = "<mojojojo>",
                    name = "woohoo",
                    photo = null,
                    title = "Whopah",
                    workplace = "Makanya"
                )
            ).await()
        }
    }
}