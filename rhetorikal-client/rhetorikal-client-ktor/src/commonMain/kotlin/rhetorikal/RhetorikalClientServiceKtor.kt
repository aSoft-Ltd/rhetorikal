package rhetorikal

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

class RhetorikalClientServiceKtor(
    override val rhetoricians: RhetoriciansClientService,
    override val statements: StatementsClientService,
) : RhetorikalClientService {
    @JvmOverloads
    constructor(
        endpoint: String,
        scope: CoroutineScope = CoroutineScope(SupervisorJob()),
        client: HttpClient = HttpClient { }
    ) : this(
        statements = StatementsClientServiceKtor(endpoint, scope, client),
        rhetoricians = RhetoriciansClientServiceKtor(endpoint, scope, client)
    )
}