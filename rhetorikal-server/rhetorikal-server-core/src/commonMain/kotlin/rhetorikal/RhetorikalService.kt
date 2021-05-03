package rhetorikal

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later

class RhetorikalService {
    val rhetoricians = RhetoriciansService()
    val statements = StatementsService()
    val scope = CoroutineScope(SupervisorJob())
    fun create(params: StatementParams): Later<Statement> = scope.later {
        val wit = rhetoricians.load(params.rhetoricianId).await()
        statements.create(params, wit).await()
    }
}