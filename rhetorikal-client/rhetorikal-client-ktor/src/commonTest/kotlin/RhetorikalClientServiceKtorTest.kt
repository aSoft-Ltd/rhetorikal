import expect.expect
import expect.toBeGreaterThan
import later.await
import test.asyncTest
import rhetorikal.services.RhetorikalClientServiceKtor
import rhetorikal.StatementParams
import rhetorikal.RhetoricianParams
import kotlin.test.Test

class RhetorikalClientServiceKtorTest {
    val service = RhetorikalClientServiceKtor(endpoint = ENDPOINT)

    @Test
    fun should_create_a_witness_then_a_testimony() = asyncTest {
        val info = RhetoricianParams(
            name = "Legit Witness",
            title = "Legit Title",
            workplace = "Legit Workplace"
        )
        val witness = service.rhetoricians.create(info).await()
        val param = StatementParams(
            rhetoricianId = witness.uid,
            statement = "Things are looking so good"
        )
        service.statements.create(param).await()
        expect(service.statements.all().await().size).toBeGreaterThan(0)
    }
}